package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Operacao implements Serializable {
    @SerializedName("minimum_initial_application_amount")
    private Double aplicacaoMinima;

    public Operacao(Double aplicacaoMinima) {
        this.aplicacaoMinima = aplicacaoMinima;
    }

    public Double getAplicacaoMinima() {
        return aplicacaoMinima;
    }

    public void setAplicacaoMinima(Double aplicacaoMinima) {
        this.aplicacaoMinima = aplicacaoMinima;
    }
}
