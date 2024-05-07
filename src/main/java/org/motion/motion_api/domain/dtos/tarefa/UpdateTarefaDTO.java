package org.motion.motion_api.domain.dtos.tarefa;

import jakarta.validation.constraints.NotBlank;

public record UpdateTarefaDTO(
        @NotBlank
        String status
) {

}
