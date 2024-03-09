package org.example.pitstop_api.application.services;

import org.example.pitstop_api.application.dtos.UpdateOficinaDTO;
import org.example.pitstop_api.application.exception.DadoUnicoDuplicadoException;
import org.example.pitstop_api.application.exception.RecursoNaoEncontradoException;
import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.repositories.IOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaService {

    @Autowired
    private IOficinaRepository oficinaRepository;

    public List<Oficina> listarOficinas() {
        return oficinaRepository.findAll();
    }

    public Oficina buscarOficinaPorId(Integer id) {
        return oficinaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id));
    }

    public Oficina criarOficina(Oficina oficina) {
        checarConflitoCnpj(oficina);
        return oficinaRepository.save(oficina);
    }

    public Oficina atualizarOficina(Integer id, UpdateOficinaDTO oficinaAtualizada) {
        Oficina oficina = oficinaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id));


        oficina.setNome(oficinaAtualizada.nome());
        oficina.setCep(oficinaAtualizada.cep());
        oficina.setNumero(oficinaAtualizada.numero());
        oficina.setComplemento(oficinaAtualizada.complemento());

        return oficinaRepository.save(oficina);
    }

    public void deletarOficina(Integer id) {
        oficinaRepository.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + id));
        oficinaRepository.deleteById(id);

    }




    /**
     * @param oficina Oficina a ser checada.
     * @return CnpjDuplicadoException caso o cnpj já esteja cadastrado.
     */
    private void checarConflitoCnpj(Oficina oficina){
        if(listarOficinas().stream().anyMatch(o->o.getCnpj().equals(oficina.getCnpj())))
            throw new DadoUnicoDuplicadoException("CNPJ já cadastrado");
    }
}
