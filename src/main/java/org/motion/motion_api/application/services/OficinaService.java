package org.motion.motion_api.application.services;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.NotImplementedException;
import org.motion.motion_api.application.dtos.oficina.UpdateOficinaDTO;
import org.motion.motion_api.application.exceptions.DadoUnicoDuplicadoException;
import org.motion.motion_api.application.exceptions.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.strategies.OficinaServiceStrategy;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.InformacoesOficina;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IInformacoesOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaService implements OficinaServiceStrategy{

    @Autowired
    private IOficinaRepository oficinaRepository;

    @Autowired
    IInformacoesOficinaRepository informacoesOficinaRepository;
    public List<Oficina> listarTodos() {
        return oficinaRepository.findAll();
    }

    public Oficina buscarPorId(int id) {
        return oficinaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id));
    }

    @Transactional
    public Oficina criar(Oficina oficina) {
        checarConflitoCnpj(oficina);
        InformacoesOficina informacoesOficina = new InformacoesOficina(
                "",
                "08",
                "18",
                "10",
                "18",
                "false;true;true;true;true;true;true",
                "carro;moto",
                "combustão"

        );
        informacoesOficinaRepository.save(informacoesOficina);
        oficina.setInformacoesOficina(informacoesOficina);
        return oficinaRepository.save(oficina);
    }

    public Oficina atualizar(int id, UpdateOficinaDTO oficinaAtualizada) {
        Oficina oficina = oficinaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id));
        oficina.setNome(oficinaAtualizada.nome());
        oficina.setCep(oficinaAtualizada.cep());
        oficina.setNumero(oficinaAtualizada.numero());
        oficina.setComplemento(oficinaAtualizada.complemento());
        oficina.setHasBuscar(oficinaAtualizada.hasBuscar());

        return oficinaRepository.save(oficina);
    }

    public void deletar(int id) {
        oficinaRepository.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id));
        oficinaRepository.deleteById(id);
        //throw new NotImplementedException("");
    }




    /**
     * @param oficina Oficina a ser checada.
     * @return CnpjDuplicadoException caso o cnpj já esteja cadastrado.
     */
    private void checarConflitoCnpj(Oficina oficina){
        if(listarTodos().stream().anyMatch(o->o.getCnpj().equals(oficina.getCnpj())))
            throw new DadoUnicoDuplicadoException("CNPJ já cadastrado");
    }
}
