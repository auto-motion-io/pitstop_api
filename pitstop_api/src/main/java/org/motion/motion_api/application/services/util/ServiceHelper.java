package org.motion.motion_api.application.services.util;

import org.hibernate.annotations.SecondaryRow;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHelper {
    /**
     * @param fkOficina
     * @return Retorna uma oficina caso encontre ou uma exceção caso não.
     * @throws RecursoNaoEncontradoException
     */

    private final IOficinaRepository oficinaRepository;

    public ServiceHelper(IOficinaRepository _oficinaRepository) {
        this.oficinaRepository = _oficinaRepository;
    }

    public  Oficina pegarOficinaValida(int fkOficina) {
        return oficinaRepository.findById(fkOficina).orElseThrow(() ->
                new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + fkOficina));

    }
}
