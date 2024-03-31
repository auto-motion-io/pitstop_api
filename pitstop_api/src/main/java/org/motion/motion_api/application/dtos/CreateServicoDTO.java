package org.motion.motion_api.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateServicoDTO(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotBlank Double valorServico,
        @NotBlank String garantia,
        @NotBlank Integer fkOficina) {
}
