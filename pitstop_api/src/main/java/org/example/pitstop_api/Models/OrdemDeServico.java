package org.example.pitstop_api.Models;

import java.util.Date;

public class OrdemDeServico {
    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private String status;
    private double valorTotal;

    public OrdemDeServico(Date dataInicio, Date dataFim, String descricao, String status, double valorTotal) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.valorTotal = valorTotal;
    }

    public OrdemDeServico() {
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
