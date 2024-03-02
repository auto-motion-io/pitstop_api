package org.example.pitstop_api.domain;

import java.time.LocalDate;

public class Tarefa {
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private String prioridade;
    private String status;

    public Tarefa(String descricao, LocalDate dataCriacao, LocalDate dataConclusao, String prioridade, String status) {
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.prioridade = prioridade;
        this.status = status;
    }

    public Tarefa() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
