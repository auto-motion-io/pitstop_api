package org.motion.motion_api.application.services;

import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.dtos.pitstop.servico.CreateServicoDTO;
import org.motion.motion_api.domain.dtos.pitstop.servico.UpdateServicoDTO;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Servico;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IOrdemDeServicoRepository;
import org.motion.motion_api.domain.repositories.pitstop.IServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    IServicoRepository servicoRepository;
    @Autowired
    IOficinaRepository oficinaRepository;
    @Autowired
    IOrdemDeServicoRepository ordemDeServicoRepository;
    @Autowired
    ServiceHelper serviceHelper;

    public Servico buscarPorId(Integer id){
        return servicoRepository.findById(id).orElseThrow(()-> new RuntimeException("Serviço não encontrado com o id: " + id));
    }
    public List<Servico> listarServicos(){
        return servicoRepository.findAll();
    }
    public Servico cadastrar(CreateServicoDTO servico){
        Servico servicoCadastrado = new Servico(servico, oficinaRepository.findById(servico.fkOficina()).orElseThrow(()-> new RuntimeException("Oficina não encontrada com o id: " + servico.fkOficina())));
        servicoRepository.save(servicoCadastrado);
        return servicoCadastrado;
    }

    public void deletar(Integer id){
        Servico servico = buscarPorId(id);
        servicoRepository.delete(servico);
    }

    public Servico atualizar(Integer id, UpdateServicoDTO servico){
        Servico servicoAtualizado = buscarPorId(id);
        servicoAtualizado.setNome(servico.getNome());
        servicoAtualizado.setDescricao(servico.getDescricao());
        servicoAtualizado.setValorServico(servico.getValorServico());
        servicoAtualizado.setGarantia(servico.getGarantia());
        servicoRepository.save(servicoAtualizado);
        return servicoAtualizado;
    }


    public List<Servico> buscarPorOficina(Integer idOficina){
        Oficina o = serviceHelper.pegarOficinaValida(idOficina);
        return servicoRepository.findByOficina(o);
    }
}
