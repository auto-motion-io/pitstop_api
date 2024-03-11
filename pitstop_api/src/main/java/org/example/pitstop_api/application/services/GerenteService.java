package org.example.pitstop_api.application.services;


import jakarta.transaction.Transactional;
import org.example.pitstop_api.application.dtos.CreateGerenteDTO;
import org.example.pitstop_api.application.dtos.LoginGerenteRequest;
import org.example.pitstop_api.application.dtos.UpdateGerenteDTO;
import org.example.pitstop_api.application.exception.DadoUnicoDuplicadoException;
import org.example.pitstop_api.application.exception.RecursoNaoEncontradoException;
import org.example.pitstop_api.application.exception.SenhaIncorretaException;
import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.pitstop.Gerente;
import org.example.pitstop_api.domain.repositories.IOficinaRepository;
import org.example.pitstop_api.domain.repositories.pitstop.IGerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GerenteService {

    @Autowired
    IGerenteRepository gerenteRepository;

    @Autowired
    IOficinaRepository oficinaRepository;


    public List<Gerente> listarGerentes(){
        return gerenteRepository.findAll();
    }

    public Gerente cadastrar(CreateGerenteDTO novoGerenteDTO) {
        Oficina oficina = pegarOficinaValida(novoGerenteDTO.fkOficina());
        verificarEmailDuplicado(novoGerenteDTO.email());
        oficinaComGerenteCadastrado(oficina);
        Gerente gerente = new Gerente(novoGerenteDTO, oficina);
        gerenteRepository.save(gerente);

        return gerente;
    }

    public Gerente login(LoginGerenteRequest request) {
        Gerente gerente = gerenteRepository.findGerenteByEmail(request.email());
        if (gerente == null) throw new RecursoNaoEncontradoException("Usuário não encontrado com email: " + request.email());
        if (!(request.senha().equals(gerente.getSenha())))
            throw new SenhaIncorretaException("Usuário não autorizado, senha incorreta");
        return gerente;
    }

    @Transactional
    public Gerente atualizar(Integer id,UpdateGerenteDTO updateGerenteDTO){
        Gerente gerente = gerenteRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Gerente não encontrado com o id: " + id));
        gerente.setNome(updateGerenteDTO.nome());
        gerente.setSobrenome(updateGerenteDTO.sobrenome());
        gerenteRepository.save(gerente);
        return gerente;
    }


    private void verificarEmailDuplicado(String email) {
        if (gerenteRepository.existsByEmail(email)) {
            throw new DadoUnicoDuplicadoException("Email já cadastrado");
        }
    }

    /**
     * @return Retorna uma oficina caso encontre ou uma exceção caso não.
     * @param fkOficina
     * @throws RecursoNaoEncontradoException
     * */
    private Oficina pegarOficinaValida(Integer fkOficina) {
       return oficinaRepository.findById(fkOficina).orElseThrow(() ->
                new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + fkOficina));

    }
    /**
     * @return void
     * @param oficina
     * @throws DadoUnicoDuplicadoException
     * */
    private void oficinaComGerenteCadastrado(Oficina oficina){
       if(gerenteRepository.existsByOficina(oficina)) throw new DadoUnicoDuplicadoException("Oficina com gerente já cadastrado");
    }

}
