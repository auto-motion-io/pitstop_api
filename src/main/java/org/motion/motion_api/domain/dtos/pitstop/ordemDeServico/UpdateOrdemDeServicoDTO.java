package org.motion.motion_api.domain.dtos.pitstop.ordemDeServico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;


public record UpdateOrdemDeServicoDTO(
        @NotNull
        Integer fkOficina,
        @NotBlank
        String status,
        @NotBlank
        String garantia,
        @NotNull
        Integer fkVeiculo,
        @NotNull
        Integer fkCliente,
        @NotNull
        Integer fkMecanico,
        @NotNull
        LocalDate dataInicio,
        @NotNull
        LocalDate dataFim,
        @NotBlank
        String tipoOs,
        @NotBlank
        List<String> produtos,
        @NotBlank
        List<String> servicos,
        @NotBlank
        String observacoes) {
}
