package org.example.pitstop_api.application.dtos;

import jakarta.validation.constraints.NotNull;
import org.example.pitstop_api.domain.entities.Oficina;

public record GerenteDTO(
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        String senha,
        @NotNull
        Integer fkOficina
) {
}
