package org.motion.motion_api.domain.dtos.oficina;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateFotoOficinaDTO {
    @NotBlank
    private String url;
}
