package org.motion.motion_api.application.services;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.commons.lang3.NotImplementedException;
import org.motion.motion_api.application.dtos.CreateGerenteDTO;
import org.motion.motion_api.application.dtos.LoginGerenteRequest;
import org.motion.motion_api.application.dtos.UpdateGerenteDTO;
import org.motion.motion_api.application.dtos.UpdateSenhaGerenteDTO;
import org.motion.motion_api.application.exception.DadoUnicoDuplicadoException;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.application.exception.SenhaIncorretaException;
import org.motion.motion_api.application.services.strategies.GerenteServiceStrategy;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IGerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GerenteService implements GerenteServiceStrategy {

    @Autowired
    IGerenteRepository gerenteRepository;

    @Autowired
    IOficinaRepository oficinaRepository;


    public List<Gerente> listarTodos() {
        return gerenteRepository.findAll();
    }

    public Gerente buscarPorId(int id) {
        return gerenteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Gerente não encontrado com o id: " + id));
    }

    public Gerente criar(CreateGerenteDTO novoGerenteDTO) {
        Oficina oficina = pegarOficinaValida(novoGerenteDTO.fkOficina());
        verificarEmailDuplicado(novoGerenteDTO.email());
        oficinaComGerenteCadastrado(oficina);
        Gerente gerente = new Gerente(novoGerenteDTO, oficina);
        gerenteRepository.save(gerente);

        return gerente;
    }


    @Transactional
    public Gerente atualizar(int id, UpdateGerenteDTO updateGerenteDTO) {
        Gerente gerente = buscarPorId(id);
        gerente.setNome(updateGerenteDTO.nome());
        gerente.setSobrenome(updateGerenteDTO.sobrenome());
        gerenteRepository.save(gerente);
        return gerente;
    }

    @Override
    public void deletar(int id) {
        throw new NotImplementedException();
    }

    @Transactional
    public Gerente atualizarSenha(int id, UpdateSenhaGerenteDTO updateSenhaGerenteDTO) {
        Gerente gerente = buscarPorId(id);
        gerente.setSenha(updateSenhaGerenteDTO.senha());
        gerenteRepository.save(gerente);
        return gerente;
    }


    public Gerente login(@Valid LoginGerenteRequest request) {
        Gerente gerente = gerenteRepository.findGerenteByEmail(request.email());
        if (gerente == null)
            throw new RecursoNaoEncontradoException("Usuário não encontrado com email: " + request.email());
        if (!(request.senha().equals(gerente.getSenha())))
            throw new SenhaIncorretaException("Usuário não autorizado, senha incorreta.");
        return gerente;
    }


    private void verificarEmailDuplicado(String email) {
        if (gerenteRepository.existsByEmail(email)) {
            throw new DadoUnicoDuplicadoException("Email já cadastrado");
        }
    }

    /**
     * @param fkOficina
     * @return Retorna uma oficina caso encontre ou uma exceção caso não.
     * @throws RecursoNaoEncontradoException
     */
    private Oficina pegarOficinaValida(int fkOficina) {
        return oficinaRepository.findById(fkOficina).orElseThrow(() ->
                new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + fkOficina));

    }

    /**
     * @param oficina
     * @return void
     * @throws DadoUnicoDuplicadoException
     */
    private void oficinaComGerenteCadastrado(Oficina oficina) {
        if (gerenteRepository.existsByOficina(oficina))
            throw new DadoUnicoDuplicadoException("Oficina com gerente já cadastrado");
    }

}
