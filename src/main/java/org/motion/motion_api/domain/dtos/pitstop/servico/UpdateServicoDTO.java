package org.motion.motion_api.domain.dtos.pitstop.servico;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateServicoDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private Double valorServico;
    @NotBlank
    private String garantia;
}
