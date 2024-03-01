package org.example.pitstop_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestClienteDTO(@NotNull @NotBlank String nome, @NotNull @NotBlank String telefone, @NotNull @NotBlank String email, @NotNull String endereco) {
}
