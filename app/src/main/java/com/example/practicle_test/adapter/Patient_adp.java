package com.example.practicle_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.practicle_test.model.Patients;
import com.example.practicle_test.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Patient_adp extends RecyclerView.Adapter<Patient_adp.Myholder> {
    Context context;
    List<Patients> lits;


    public Patient_adp(Context context, List<Patients> lits) {
        this.context = context;
        this.lits = lits;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myholder holder, final int position) {

        holder.name.setText(lits.get(position).getPname());
        holder.weight.setText(lits.get(position).getWeight());
        holder.dob.setText(lits.get(position).getDob());
        holder.gender.setText(lits.get(position).getGender());
        holder.contact.setText(lits.get(position).getContact());
        holder.email.setText(lits.get(position).getEmail());
        holder.disease.setText(lits.get(position).getDisease());


    }

    @Override
    public int getItemCount() {
        return lits.size();
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView name, weight, contact, email, dob, gender, disease;
        ImageView iv;


        public Myholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.p_name);
            weight = itemView.findViewById(R.id.p_weight);
            contact = itemView.findViewById(R.id.p_contact);
            email = itemView.findViewById(R.id.p_email);
            dob = itemView.findViewById(R.id.b_date);
            gender = itemView.findViewById(R.id.p_gender);
            disease = itemView.findViewById(R.id.p_disease);


        }


    }

}
