package org.motion.motion_api.domain.dtos.pitstop.gerente;

import org.motion.motion_api.domain.entities.Oficina;

public record LoginGerenteResponse
        (
                Integer idGerente,
                String email,
                String nome,
                String sobrenome,
                Oficina oficina,
                String token
         )
{
}
