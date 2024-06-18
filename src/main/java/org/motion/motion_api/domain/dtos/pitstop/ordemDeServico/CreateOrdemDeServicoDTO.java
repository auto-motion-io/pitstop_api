package org.motion.motion_api.domain.dtos.pitstop.ordemDeServico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.motion.motion_api.domain.dtos.pitstop.produtoEstoque.ProdutoOrdemDTO;
import org.motion.motion_api.domain.dtos.pitstop.servico.ServicoOrdemDTO;

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
        List<ProdutoOrdemDTO> produtos,
        List<ServicoOrdemDTO> servicos,
        String observacoes,
        Double valorTotal,
        Integer quantidade) {
}
