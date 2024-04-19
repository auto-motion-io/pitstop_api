package org.motion.motion_api.application.dtos.tarefa;

import jakarta.validation.constraints.NotBlank;

public record UpdateTarefaDTO(
        @NotBlank
        String status
) {

}
