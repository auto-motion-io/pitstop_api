package org.motion.motion_api.domain.dtos.pitstop.mecanico;

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
