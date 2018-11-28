package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Video implements Serializable {
    @SerializedName("thumbnail")
    private String urlImagem;

    public Video(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
