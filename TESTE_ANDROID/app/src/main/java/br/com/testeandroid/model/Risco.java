package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Risco implements Serializable {
    @SerializedName("score_range_order")
    private int valor;

    @SerializedName("name")
    private String descricao;

    public Risco(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
