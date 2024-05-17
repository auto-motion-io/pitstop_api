package org.motion.motion_api.domain.dtos.oficina;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SetInfoOficinaDTO {
    @NotBlank
    private String whatsapp;
    @NotBlank
    private String horarioIniSem;
    @NotBlank
    private String horarioFimSem;
    @NotBlank
    private String horarioIniFds;
    @NotBlank
    private String horarioFimFds;
    @NotBlank
    private String diasSemanaAberto;
    @NotBlank
    private String tipoVeiculosTrabalha;
    @NotBlank
    private String tipoPropulsaoTrabalha;
}
