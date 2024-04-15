package org.motion.motion_api.application.dtos.ordemDeServico;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;


public record CreateOrdemDeServicoDTO(
        @NotBlank
        Integer fkOficina,
        @NotBlank
        String status,
        @NotBlank
        String garantia,
        @NotBlank
        String token,
        @NotBlank
        Integer fkVeiculo,
        @NotBlank
        Integer fkCliente,
        @NotBlank
        Integer fkMecanico,
        @NotBlank
        LocalDate dataInicio,
        @NotBlank
        LocalDate dataFim,
        @NotBlank
        String tipoOs,
        @NotBlank
        String tipo,
        @NotBlank
        List<String> produtos,
        @NotBlank
        List<String> servicos,
        @NotBlank
        String observacoes) {
}
