package com.example.greenplant.Model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class HumTemp {

    private String Humedad;
    private String Temperatura;

    public HumTemp() {
    }

    public HumTemp(String humedad, String temperatura) {
        Humedad = humedad;
        Temperatura = temperatura;
    }

    public String getHumedad() {
        return Humedad;
    }

    public void setHumedad(String humedad) { this.Humedad = humedad; }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.Temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "HumTemp{" +
                "Humedad='" + Humedad + '\'' +
                ", Temperatura='" + Temperatura + '\'' +
                '}';
    }
}
