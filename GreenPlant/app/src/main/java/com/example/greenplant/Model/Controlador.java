package com.example.greenplant.Model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class Controlador {
    private float humedadTierra;
    private float temperaturaAire;
    private String humStr;
    private String temStr;

    public Controlador(float humedadTierra, float temperaturaAire, String humStr, String tempStr) {
        this.humedadTierra = humedadTierra;
        this.temperaturaAire = temperaturaAire;
        this.humStr = humStr;
        this.temStr = tempStr;
    }

    public Controlador(){

    }

    public String getTemStr() {
        return temStr;
    }

    public void setTemStr(String temStr) {
        this.temStr = temStr;
    }

    public String getHumStr() {
        return humStr;
    }

    public void setHumStr(String humStr) {
        this.humStr = humStr;
    }

    public float getHumedadTierra() {
        return humedadTierra;
    }

    public void setHumedadTierra( float humedadTierra) { this.humedadTierra = humedadTierra; }

    public float getTemperaturaAire() {
        return temperaturaAire;
    }

    public void setTemperaturaAire(float temperaturaAire) {
        this.temperaturaAire = temperaturaAire;
    }
}
