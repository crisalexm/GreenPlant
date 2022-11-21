package com.example.greenplant;


import java.io.Serializable;

public class Planta implements Serializable {

    private String name;
    private String familyName;
    // Tipo Array List

    public Planta(String name, String scientificName) {
        this.name = name;
        this.familyName = scientificName;
    }

    public Planta(){

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
