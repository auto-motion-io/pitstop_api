package org.motion.motion_api.application.dtos.produtoEstoque;

import jakarta.validation.constraints.NotBlank;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;

public record CreateProdutoEstoqueDTO(
        @NotBlank
        Integer fkProduto,
        @NotBlank
        Integer fkOficina,
        @NotBlank
        String nome,
        @NotBlank
        Integer quantidade,
        @NotBlank
        Double valorVenda
) {
}
