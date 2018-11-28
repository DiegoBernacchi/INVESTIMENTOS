package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Descricao implements Serializable {

    @SerializedName("objective")
    private String objetivo;

    @SerializedName("target_audience")
    private String publicoAlvo;

    public Descricao(String objetivo, String publicoAlvo) {
        this.objetivo = objetivo;
        this.publicoAlvo = publicoAlvo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }
}
