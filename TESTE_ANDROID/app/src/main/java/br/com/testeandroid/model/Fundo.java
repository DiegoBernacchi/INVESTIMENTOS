package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class Fundo implements Serializable {
    @SerializedName("simple_name")
    private String nomeDoFundo;

    @SerializedName("full_name")
    private String nomeCompleto;

    @SerializedName("operability")
    private Operacao operacao;

    @SerializedName("specification")
    private Especificacao especificacao;

    @SerializedName("description")
    private Descricao descricao;

    @SerializedName("initial_date")
    private Date dataInicio;

    @SerializedName("strategy_video")
    private Video video;

    public Fundo(String nomeDoFundo, String nomeCompleto, Operacao operacao, Especificacao especificacao, Descricao descricao, Date dataInicio, Video video) {
        this.nomeDoFundo = nomeDoFundo;
        this.nomeCompleto = nomeCompleto;
        this.operacao = operacao;
        this.especificacao = especificacao;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.video = video;
    }

    public String getNomeDoFundo() {
        return nomeDoFundo;
    }

    public void setNomeDoFundo(String nomeDoFundo) {
        this.nomeDoFundo = nomeDoFundo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public Descricao getDescricao() {
        return descricao;
    }

    public void setDescricao(Descricao descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
