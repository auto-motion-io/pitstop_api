package org.example.pitstop_api.domain;

public class ProdutoEstoque {
    private String nome;
    private String descricao;
    private int quantidade;
    private String localizacao;
    private double valorCompra;
    private double valorVenda;

    public ProdutoEstoque(String nome, String descricao, int quantidade, String localizacao, double valorCompra, double valorVenda) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.localizacao = localizacao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
    }

    public ProdutoEstoque() {
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}
