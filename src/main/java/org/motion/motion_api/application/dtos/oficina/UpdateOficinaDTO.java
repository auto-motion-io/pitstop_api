package org.motion.motion_api.application.dtos.oficina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateOficinaDTO(
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String cep,
        @NotNull @NotBlank
        String numero,
        String complemento,
        boolean hasBuscar
) {
};
