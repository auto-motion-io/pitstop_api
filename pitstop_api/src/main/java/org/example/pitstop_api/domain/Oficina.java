package org.example.pitstop_api.domain;

public class Oficina {
    private String nome;
    private String cep;
    private int numero;
    private String complemento;

    public Oficina(String nome, String cep, int numero, String complemento) {
        this.nome = nome;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Oficina() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
