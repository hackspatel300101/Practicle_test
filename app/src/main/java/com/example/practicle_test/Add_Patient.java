package com.example.practicle_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.practicle_test.model.Patients;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Add_Patient extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText name,contact,email_Add,dob,weight;
    MultiAutoCompleteTextView disease;
    ImageView photo;
    Button submit_btn,browse;
    private Bitmap bitmap;
    private  final int IMG_REQUEST=1;
    public int check_img=0;
    long sizeofpic;
    RadioGroup radioSexGroup;
    String nm,em,con,strdob,strweight,dis,gender;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__patient);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        email_Add=findViewById(R.id.email);
        dob=findViewById(R.id.birthday);
        photo=findViewById(R.id.photo);
        weight=findViewById(R.id.weigt);
        disease=findViewById(R.id.disease);
        submit_btn=findViewById(R.id.submit_btn);
        browse=findViewById(R.id.browse);
        radioSexGroup=findViewById(R.id.radioGrp);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        String str1[] =getResources().getStringArray(R.array.Disease);
        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,str1);
        disease.setAdapter(adapter);
        disease.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Add_Patient.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });
        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group == radioSexGroup) {
                    RadioButton rb = findViewById(checkedId);
                    gender = (String) rb.getText();
                   // Toast.makeText(Add_Patient.this, ""+gender, Toast.LENGTH_SHORT).show();
                    Log.i("msg", "onCheckedChanged: "+gender);
                }

            }
        });

        submit_btn.setOnClickListener(this);
        browse.setOnClickListener(this);


    }

    private void updateLabel() {
        String myFormat = "dd/MM/YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.browse:
                check_img=1;
                selectImage();
                break;
            case R.id.submit_btn:
                SubmitData();
                break;

        }
    }
    private void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);

    }
    private String ImageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgeByte=byteArrayOutputStream.toByteArray();
        sizeofpic= imgeByte.length;
        Toast.makeText(Add_Patient.this," String ="+imgeByte,Toast.LENGTH_LONG).show();

        return Base64.encodeToString(imgeByte,Base64.DEFAULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            try {

                Uri path = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);

                photo.setImageBitmap(bitmap);
                Toast.makeText(Add_Patient.this,"Path ="+path+" Bitmap  ="+bitmap,Toast.LENGTH_LONG).show();

            }catch(IOException e)
            {
                Toast.makeText(Add_Patient.this,"Error "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
    public void SubmitData()
    {

        if(CheckEmpty()) {

                final String img = ImageToString(bitmap);
                nm=name.getText().toString();
                con=contact.getText().toString();
                em=email_Add.getText().toString();
                strdob=dob.getText().toString();
                strweight=weight.getText().toString();
                dis=disease.getText().toString();
                Patients patients = new Patients();
                patients.setImagerul(img);
                patients.setPname(nm);
                patients.setContact(con);
                patients.setEmail(em);
                patients.setDob(strdob);
                patients.setGender(gender);
                patients.setWeight(strweight);
                patients.setDisease(dis);
                DBHelper dbHelper = new DBHelper(Add_Patient.this);
                dbHelper.dataInsert(patients);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                name.getText().clear();
                contact.getText().clear();
                email_Add.getText().clear();
                dob.getText().clear();
                weight.getText().clear();
                disease.getText().clear();
                disease.clearFocus();
                //radioSexGroup.clearCheck();
                photo.setImageResource(R.mipmap.ic_launcher);



        }

    }

    public boolean CheckEmpty()
    {  // check the You enter number or anything else


        if(name.getText().toString().isEmpty())
        {
            name.setError("Invalid Input");
            return false;

        }else if(contact.getText().toString().isEmpty())
        {   contact.setError("Invalid Input");
            return  false;
        }
        else if(email_Add.getText().toString().isEmpty())
        {   email_Add.setError("Invalid Input");
            return  false;
        }
        else if(dob.getText().toString().isEmpty())
        {   dob.setError("Invalid Input");
            return  false;
        }
        else if(gender.equals(null))
        {   email_Add.setError("Invalid Input");
            return  false;
        }
        else if(disease.getText().toString().isEmpty())
        {   disease.setError("Invalid Input");
            return  false;
        }else if(weight.getText().toString().isEmpty())
        {
            weight.setError("Invalid Input");
            return false;
        }else if(check_img==0)
        {
            browse.setError("Please select Image");
            return false;
        }else
        {
            return true;
        }
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        {
            Intent i = new Intent(Add_Patient.this,HomeActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Add_Patient.this,HomeActivity.class);
        startActivity(i);
        finish();
    }


}