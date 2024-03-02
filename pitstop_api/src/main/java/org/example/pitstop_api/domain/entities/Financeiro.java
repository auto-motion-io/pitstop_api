package org.example.pitstop_api.domain;

import java.time.LocalDate;

public class Financeiro {
    private String tipo;
    private String descricao;
    private LocalDate data;
    private double valor;
    private String categoria;
    private String formaPagamento;

    public Financeiro(String tipo, String descricao, LocalDate data, double valor, String categoria, String formaPagamento) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
    }

    public Financeiro() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
