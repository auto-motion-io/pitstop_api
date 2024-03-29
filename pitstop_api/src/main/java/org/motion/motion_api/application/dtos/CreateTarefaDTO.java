package org.motion.motion_api.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTarefaDTO(
        @NotBlank
        String descricao,
        @NotBlank
        LocalDate dtDeadline,
        @NotBlank
        String status,
        @NotNull
        Integer fkOficina
        )
        {}
