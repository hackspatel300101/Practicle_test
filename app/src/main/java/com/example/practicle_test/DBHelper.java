package com.example.practicle_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.example.practicle_test.model.Patients;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "mydb";
    public static final String TBLNAME = "patient";
    public static final String ID = "id";
    public static final String NAME = "pname";
    public static final String CONTACT = "contact";
    public static final String EMAIL = "email";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String WEIGHT = "weight";
    public static final String IMAGEURL = "imagerul";
    public static final String DISEASE = "disease";
    public static final int VERSION = 1;
    public DBHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+TBLNAME+"("+ID+" integer primary key autoincrement,"+NAME+" text,"+CONTACT+" text,"+EMAIL+" text,"+DOB+" text,"+GENDER+" text,"+WEIGHT+" text,"+IMAGEURL+" text,"+DISEASE+" text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void dataInsert(Patients patients)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,patients.getPname());
        cv.put(CONTACT,patients.getContact());
        cv.put(EMAIL,patients.getEmail());
        cv.put(DOB,patients.getDob());
        cv.put(GENDER,patients.getGender());
        cv.put(WEIGHT,patients.getWeight());
        cv.put(IMAGEURL, patients.getImagerul());
        cv.put(DISEASE,patients.getDisease());
        db.insert(TBLNAME,ID,cv);
        db.close();
    }
    public List<Patients> show()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Patients> arrayList = new ArrayList<>();
        String column[]= {ID,NAME,CONTACT,EMAIL,DOB,GENDER,WEIGHT,IMAGEURL,DISEASE};
        Cursor cursor = db.query(TBLNAME,column,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String contact= cursor.getString(2);
            String email= cursor.getString(3);
            String dob=cursor.getString(4);
            String gender=cursor.getString(5);
            String weight=cursor.getString(6);
            String imageurl = cursor.getString(7);
            String disease = cursor.getString(8);

            Patients patients = new Patients();
            patients.setId(id);
            patients.setPname(name);
            patients.setContact(contact);
            patients.setEmail(email);
            patients.setDob(dob);
            patients.setGender(gender);
            patients.setWeight(weight);
            patients.setImagerul(imageurl);
            patients.setDisease(disease);
            arrayList.add(patients);

        }
        return arrayList;

    }


}
