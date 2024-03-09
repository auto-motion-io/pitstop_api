package org.example.pitstop_api.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateSenhaGerenteDTO(
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String sobrenome
) {
}

