package org.example.pitstop_api.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.pitstop_api.domain.entities.Oficina;

public record GerenteDTO(
        @NotNull @NotBlank
        String email,
        @NotNull @NotBlank
        String senha,
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String sobrenome,
        @NotNull @NotBlank
        Integer fkOficina
) {
}
