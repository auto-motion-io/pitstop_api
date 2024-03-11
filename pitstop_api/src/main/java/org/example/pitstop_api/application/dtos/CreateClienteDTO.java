package org.example.pitstop_api.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateClienteDTO(
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String telefone,
        @NotNull @NotBlank
        String email
) {
}
