package com.example.practicle_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.practicle_test.model.Diseasedata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class Report extends AppCompatActivity {
    ListView lv;
    List<Diseasedata> list = new ArrayList<>();
    RequestQueue requestQueue;
    int id;
    String title;
    String URL = "http://demomyurl.com/webtest/disease.php";
    String URL1 = "http://demomyurl.com/webtest/members.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        getdata();






    }
    private void getdata() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                if(response!=null) {

                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("diseaseData");

                        TableLayout tv=(TableLayout) findViewById(R.id.table);
                        tv.removeAllViewsInLayout();
                        int flag=1;

                        // when i=-1, loop will display heading of each column
                        // then usually data will be display from i=0 to jArray.length()
                        for(int i=-1;i<jsonArray.length();i++){

                            TableRow tr=new TableRow(Report.this);

                            tr.setLayoutParams(new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

                            // this will be executed once
                            if(flag==1){

                                TextView b3=new TextView(Report.this);
                                b3.setText("column heading 1");
                                b3.setTextColor(Color.BLUE);
                                b3.setTextSize(15);
                                tr.addView(b3);

                                TextView b4=new TextView(Report.this);
                                b4.setPadding(10, 0, 0, 0);
                                b4.setTextSize(15);
                                b4.setText("column heading 2");
                                b4.setTextColor(Color.BLUE);
                                tr.addView(b4);

                                TextView b5=new TextView(Report.this);
                                b5.setPadding(10, 0, 0, 0);
                                b5.setText("column heading 3");
                                b5.setTextColor(Color.BLUE);
                                b5.setTextSize(15);
                                tr.addView(b5);
                                tv.addView(tr);

                                final View vline = new View(Report.this);
                                vline.setLayoutParams(new
                                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                                vline.setBackgroundColor(Color.BLUE);
                                tv.addView(vline); // add line below heading
                                flag=0;
                            } else {
                                JSONObject json_data = jsonArray.getJSONObject(i);

                                TextView b=new TextView(Report.this);
                                String str=String.valueOf(json_data.getInt("id"));
                                b.setText(str);
                                b.setTextColor(Color.RED);
                                b.setTextSize(15);
                                tr.addView(b);

                                TextView b1=new TextView(Report.this);
                                b1.setPadding(10, 0, 0, 0);
                                b1.setTextSize(15);
                                String str1=json_data.getString("title");
                                b1.setText(str1);
                                b1.setTextColor(Color.RED);
                                tr.addView(b1);

                                tv.addView(tr);
                                final View vline1 = new View(Report.this);
                                vline1.setLayoutParams(new
                                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                                vline1.setBackgroundColor(Color.WHITE);
                                tv.addView(vline1);  // add line below each row
                            }
                        }
                    }catch(JSONException e){
                        Log.e("log_tag", "Error parsing data " + e.toString());
                        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                    }



                }else
                {
                    Toast.makeText(Report.this, "null", Toast.LENGTH_LONG).show();

                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Toast.makeText( Report.this,"Volley Response error"+error.getMessage(),Toast.LENGTH_LONG).show();
            }

        });


        requestQueue.add(stringRequest);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        {
            Intent i = new Intent(Report.this,HomeActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Report.this,HomeActivity.class);
        startActivity(i);
        finish();
    }


}







