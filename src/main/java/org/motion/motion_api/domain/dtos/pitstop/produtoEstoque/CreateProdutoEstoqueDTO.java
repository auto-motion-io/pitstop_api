package org.motion.motion_api.domain.dtos.pitstop.produtoEstoque;

import jakarta.validation.constraints.NotBlank;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;

public record CreateProdutoEstoqueDTO(
        @NotBlank
        String nome,
        @NotBlank
        String modeloVeiculo,
        @NotBlank
        Integer quantidade,
        @NotBlank
        String localizacao,
        @NotBlank
        Double valorCompra,
        @NotBlank
        Double valorVenda,
        @NotBlank
        Double valorComMaoObra,
        @NotBlank
        String garantia,
        @NotBlank
        Integer fkOficina
) {
}
