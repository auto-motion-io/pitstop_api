package org.motion.motion_api.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateOrdemDeServicoDTO(
        @NotBlank
        Integer fkOficina,
        @NotBlank
        Integer fkVeiculo,
        @NotBlank
        Integer fkMecanico,
        @NotBlank
        Integer fkServico,
        @NotBlank
        Integer fkProdutoEstoque,
        @NotBlank
        String descricao,
        @NotBlank
        String dataInicio,
        @NotBlank
        String dataFim,
        @NotBlank
        String garantia,
        @NotBlank
        String status) {
}
