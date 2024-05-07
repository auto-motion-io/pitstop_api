package org.motion.motion_api.domain.dtos.gerente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateGerenteDTO(
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String sobrenome
) {
}


