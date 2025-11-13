package org.acme.model;

public class AtividadeBemEstar {
    private int id;
    private String nome;
    private String descricao;
    private String categoria; // "respiração", "meditação", "alongamento"
    private int duracao; // em minutos

    public AtividadeBemEstar(int id, String nome, String descricao, String categoria, int duracao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.duracao = duracao;
    }

    public AtividadeBemEstar(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
