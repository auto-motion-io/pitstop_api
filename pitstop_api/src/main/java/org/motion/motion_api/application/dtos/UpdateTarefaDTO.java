package org.motion.motion_api.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateTarefaDTO(
        @NotBlank
        String status
) {

}
