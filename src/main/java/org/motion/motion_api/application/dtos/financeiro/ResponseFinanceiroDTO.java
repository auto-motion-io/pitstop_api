package org.motion.motion_api.application.dtos.financeiro;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseFinanceiroDTO {
    private Integer idMovimento;
    private String transacao;
    private String categoria;
    private LocalDate data;
    private Double valor;
    private String formaPagamento;
}
