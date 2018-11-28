package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Especificacao implements Serializable {
    @SerializedName("fund_suitability_profile")
    private Risco risco;

    public Especificacao(Risco risco) {
        this.risco = risco;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }
}
