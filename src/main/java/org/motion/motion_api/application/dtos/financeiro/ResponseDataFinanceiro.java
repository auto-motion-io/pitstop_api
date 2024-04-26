package org.motion.motion_api.application.dtos.financeiro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDataFinanceiro {
    private Double entradas;
    private Double saidas;
    private Double saldo;
}
