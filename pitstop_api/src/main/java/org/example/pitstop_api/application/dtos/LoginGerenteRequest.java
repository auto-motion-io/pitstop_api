package org.example.pitstop_api.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginGerenteRequest(
        @NotNull @NotBlank
        String email,
        @NotNull @NotBlank
        String senha
) { }
