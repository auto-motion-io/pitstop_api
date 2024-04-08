package org.motion.motion_api.application.services;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.commons.lang3.NotImplementedException;
import org.motion.motion_api.application.dtos.gerente.CreateGerenteDTO;
import org.motion.motion_api.application.dtos.gerente.LoginGerenteRequest;
import org.motion.motion_api.application.dtos.gerente.UpdateGerenteDTO;
import org.motion.motion_api.application.dtos.gerente.UpdateSenhaGerenteDTO;
import org.motion.motion_api.application.exception.DadoUnicoDuplicadoException;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.application.exception.SenhaIncorretaException;
import org.motion.motion_api.application.services.strategies.GerenteServiceStrategy;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IGerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class GerenteService implements GerenteServiceStrategy {

    @Autowired
    IGerenteRepository gerenteRepository;

    @Autowired
    IOficinaRepository oficinaRepository;
    @Autowired
    ServiceHelper serviceHelper;


    public List<Gerente> listarTodos() {
        return gerenteRepository.findAll();
    }

    public Gerente buscarPorId(int id) {
        return gerenteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Gerente não encontrado com o id: " + id));
    }

    public Gerente criar(CreateGerenteDTO novoGerenteDTO) {
        Oficina oficina = serviceHelper.pegarOficinaValida(novoGerenteDTO.fkOficina());
        verificarEmailDuplicado(novoGerenteDTO.email());
        oficinaComGerenteCadastrado(oficina);
        String senhaGerada = UUID.randomUUID().toString();
        senhaGerada = new BCryptPasswordEncoder().encode("123");
        Gerente gerente = new Gerente(novoGerenteDTO, oficina,senhaGerada);
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
     * @param oficina
     * @return void
     * @throws DadoUnicoDuplicadoException
     */
    private void oficinaComGerenteCadastrado(Oficina oficina) {
        if (gerenteRepository.existsByOficina(oficina))
            throw new DadoUnicoDuplicadoException("Oficina com gerente já cadastrado");
    }

}
