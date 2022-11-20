package com.example.greenplant;

public class Controlador {
    private float humedadTierra;
    private int temperaturaAire;

    public Controlador(float humedadTierra, int temperaturaAire) {
        this.humedadTierra = humedadTierra;
        this.temperaturaAire = temperaturaAire;
    }

    public Controlador(){

    }

    public float getHumedadTierra() {
        return humedadTierra;
    }

    public void setHumedadTierra(float humedadTierra) {
        this.humedadTierra = humedadTierra;
    }

    public int getTemperaturaAire() {
        return temperaturaAire;
    }

    public void setTemperaturaAire(int temperaturaAire) {
        this.temperaturaAire = temperaturaAire;
    }
}
