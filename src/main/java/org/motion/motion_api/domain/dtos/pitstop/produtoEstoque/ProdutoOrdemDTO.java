package org.motion.motion_api.domain.dtos.pitstop.produtoEstoque;


public record ProdutoOrdemDTO(
        String nome,
        Integer quantidade,
        Double valorVenda,
        String garantia
) {
}
