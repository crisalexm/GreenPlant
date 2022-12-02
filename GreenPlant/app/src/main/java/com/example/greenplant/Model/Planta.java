package com.example.greenplant.Model;


import java.io.Serializable;

public class Planta implements Serializable {

    private String name;
    private String familyName;
    private String id;
    // Tipo Array List

    public Planta(String name, String scientificName, String id) {
        this.name = name;
        this.familyName = scientificName;
        this.id = id;
    }

    public Planta(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String scientificName) {
        this.familyName = scientificName;
    }
}
