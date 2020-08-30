package com.example.practicle_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicle_test.R;
import com.example.practicle_test.model.Patients;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomeAdpter extends BaseAdapter {
    Context context;
    ArrayList<Patients> arrayList;

        CustomeAdpter(Context context, ArrayList<Patients> arrayList)
        {
            this .context= context;
            this.arrayList=arrayList;

        }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            Patients user = arrayList.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_view,null);
        TextView tv1=v.findViewById(R.id.p_name);
        TextView tv2 =v.findViewById(R.id.p_contact);
        TextView tv3 = v.findViewById(R.id.p_email);
        TextView tv4 = v.findViewById(R.id.b_date);
        TextView tv5 =v.findViewById(R.id.p_gender);
        TextView tv6 = v.findViewById(R.id.p_weight);
        TextView tv7 = v.findViewById(R.id.p_disease);

        
        tv1.setText(user.getPname());
        tv2.setText(user.getContact());
        tv3.setText(user.getEmail());
        tv4.setText(user.getDob());
        tv5.setText(user.getGender());
        tv6.setText(user.getWeight());
        tv7.setText(user.getDisease());




        return v;
    }
}
