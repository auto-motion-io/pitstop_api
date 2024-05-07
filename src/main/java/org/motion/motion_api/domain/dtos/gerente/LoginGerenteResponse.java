package org.motion.motion_api.domain.dtos.gerente;

import org.motion.motion_api.domain.entities.Oficina;

public record LoginGerenteResponse
        (
                Integer idGerente,
                String email,
                String sobrenome,
                String status,
                Oficina oficina,
                String token
         )
{
}
