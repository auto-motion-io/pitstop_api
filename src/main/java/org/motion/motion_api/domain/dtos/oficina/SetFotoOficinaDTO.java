package org.motion.motion_api.domain.dtos.oficina;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SetFotoOficinaDTO {
    @NotBlank
    private String url;
    @NotNull
    private Integer oficinaId;
}
