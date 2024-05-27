package org.motion.motion_api.domain.dtos.pitstop.produtoEstoque;

import jakarta.validation.constraints.NotBlank;

public record UpdateProdutoEstoqueDTO(
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
