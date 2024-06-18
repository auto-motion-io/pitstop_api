package org.motion.motion_api.domain.dtos.pitstop.ordemDeServico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;


public record CreateOrdemDeServicoDTO(
        @NotNull
        Integer fkOficina,
        @NotBlank
        String status,
        @NotBlank
        String garantia,
        @NotBlank
        String token,
        @NotNull
        Integer fkVeiculo,
        @NotNull
        Integer fkCliente,
        Integer fkMecanico,
        @NotNull
        LocalDate dataInicio,
        LocalDate dataFim,
        String tipoOs,
        List<String> produtos,
        List<String> servicos,
        String observacoes) {
}
