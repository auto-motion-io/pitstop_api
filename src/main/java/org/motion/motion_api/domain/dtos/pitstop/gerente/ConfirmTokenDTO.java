package org.motion.motion_api.domain.dtos.pitstop.gerente;

import lombok.Data;

@Data
public class ConfirmTokenDTO {
    private String email;
    private String token;
}
