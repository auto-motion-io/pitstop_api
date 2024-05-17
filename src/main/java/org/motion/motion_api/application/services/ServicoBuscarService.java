package org.motion.motion_api.application.services;

import org.motion.motion_api.domain.dtos.buscar.CreateServicoBuscarDTO;
import org.motion.motion_api.domain.entities.buscar.ServicoBuscar;
import org.motion.motion_api.domain.repositories.buscar.IServicoBuscarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoBuscarService {

    @Autowired
    private IServicoBuscarRepository servicoBuscarRepository;

    public List<ServicoBuscar> listarServicos() {
        return servicoBuscarRepository.findAll();
    }

    public ServicoBuscar buscarServicoPorId(Integer id) {
        return servicoBuscarRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Serviço não encontrado com o id: " + id));
    }

    public ServicoBuscar cadastrar(CreateServicoBuscarDTO novoServicoBuscar) {
        ServicoBuscar servicoBuscar = new ServicoBuscar(novoServicoBuscar);
        return servicoBuscarRepository.save(servicoBuscar);
    }

    public void deletarServico(Integer id) {
        servicoBuscarRepository.deleteById(id);
    }

}
