package com.example.practicle_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;

import com.example.practicle_test.adapter.Patient_adp;
import com.example.practicle_test.model.Patients;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Patient_list extends AppCompatActivity {
   // RecyclerView lv;
    ListView lv;
    ArrayList<Patients> arrayList;
    Patient_adp adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        lv=findViewById(R.id.recycle_patient_list);
        refresh();




    }
   /* public void refresh()
    {
        ArrayList arrayList = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(Patient_list.this);
        arrayList=(ArrayList<Patients>) dbHelper.show();
         adp = new Patient_adp(Patient_list.this,arrayList);
        lv.setAdapter(adp);

    }*/
   public void refresh()
   {
       ArrayList arrayList = new ArrayList<>();
       DBHelper dbHelper = new DBHelper(Patient_list.this);
       arrayList=(ArrayList<Patients>) dbHelper.show();
       CustomeAdpter ad = new CustomeAdpter(Patient_list.this,arrayList);
       lv.setAdapter(ad);

   }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        {
            Intent i = new Intent(Patient_list.this,HomeActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Patient_list.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}