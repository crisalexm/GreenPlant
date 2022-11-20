package com.example.greenplant;

public class Planta {

    private String name;
    private String scientificName;
    // Tipo Array List

    public Planta(String name, String scientificName) {
        this.name = name;
        this.scientificName = scientificName;
    }

    public Planta(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
}
