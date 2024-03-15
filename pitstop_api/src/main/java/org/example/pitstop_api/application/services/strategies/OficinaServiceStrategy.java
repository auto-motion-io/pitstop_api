package org.example.pitstop_api.application.services.strategies;

import org.example.pitstop_api.application.dtos.UpdateOficinaDTO;
import org.example.pitstop_api.domain.entities.Oficina;

public interface OficinaServiceStrategy extends BaseService<Oficina,UpdateOficinaDTO> {
    void checarConflitoCnpj(Oficina oficina);
}
