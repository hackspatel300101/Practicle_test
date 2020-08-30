package com.example.practicle_test.model;

public class PatieantsData {
    int id;
    String name;
    String disease_ids;

    public PatieantsData(int id, String name, String disease_ids) {
        this.id = id;
        this.name = name;
        this.disease_ids = disease_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease_ids() {
        return disease_ids;
    }

    public void setDisease_ids(String disease_ids) {
        this.disease_ids = disease_ids;
    }

}
