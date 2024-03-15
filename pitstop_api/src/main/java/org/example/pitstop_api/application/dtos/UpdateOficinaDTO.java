package org.example.pitstop_api.application.dtos;

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
        @NotNull @NotBlank
        boolean hasBuscar
) {
};
