package org.acme.model;

public class MensagemApoio {
    private int id;
    private String titulo;
    private String conteudo;
    private String tipo; // "motivacional", "calma", "alerta"
    private String dataEnvio;

    public MensagemApoio(int id, String titulo, String conteudo, String tipo, String dataEnvio) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.tipo = tipo;
        this.dataEnvio = dataEnvio;
    }

    public MensagemApoio(){

    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


