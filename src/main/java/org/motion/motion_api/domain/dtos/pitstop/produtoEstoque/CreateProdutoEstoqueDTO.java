package org.motion.motion_api.domain.dtos.pitstop.produtoEstoque;

import jakarta.validation.constraints.NotBlank;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;

public record CreateProdutoEstoqueDTO(
        @NotBlank
        String nome,
        @NotBlank
        Integer quantidade,
        @NotBlank
        Double valorVenda,
        @NotBlank
        Integer fkOficina
) {
}
