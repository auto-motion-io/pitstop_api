package org.motion.motion_api.application.dtos.mecanico;

import jakarta.validation.constraints.NotBlank;

public record CreateMecanicoDTO(
        @NotBlank
        Integer fkOficina,
        @NotBlank
        String nome,
        @NotBlank
        String telefone
) {
}
