package org.acme.model;

public class SessaoEmocional {
    private int id;
    private String dataSessao;
    private String humor; // "feliz", "triste", "ansioso", etc.
    private String observacoes;

    public SessaoEmocional(int id, String dataSessao, String humor, String observacoes) {
        this.id = id;
        this.dataSessao = dataSessao;
        this.humor = humor;
        this.observacoes = observacoes;
    }

    public SessaoEmocional(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(String dataSessao) {
        this.dataSessao = dataSessao;
    }

    public String getHumor() {
        return humor;
    }

    public void setHumor(String humor) {
        this.humor = humor;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
