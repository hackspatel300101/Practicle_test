package com.example.practicle_test.model;

public class Patients {
    int id;
    String pname;
    String contact;
    String email;
    String dob;
    String weight;
    String imagerul;
    String disease;
    String gender;

    @Override
    public String toString() {
        return "Patients{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", weight='" + weight + '\'' +
                ", imagerul='" + imagerul + '\'' +
                ", disease='" + disease + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImagerul() {
        return imagerul;
    }

    public void setImagerul(String imagerul) {
        this.imagerul = imagerul;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
