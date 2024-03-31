package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.CreateOrdemDeServicoDTO;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Mecanico;
import org.motion.motion_api.domain.entities.pitstop.OrdemDeServico;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.motion.motion_api.domain.entities.pitstop.Veiculo;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IMecanicoRepository;
import org.motion.motion_api.domain.repositories.pitstop.IOrdemDeServicoRepository;
import org.motion.motion_api.domain.repositories.pitstop.IProdutoEstoqueRepository;
import org.motion.motion_api.domain.repositories.pitstop.IVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdemDeServicoService {

    @Autowired
    IOrdemDeServicoRepository ordemDeServicoRepository;
    @Autowired
    IOficinaRepository oficinaRepository;
    @Autowired
    IVeiculoRepository veiculoRepository;
    @Autowired
    IMecanicoRepository mecanicoRepository;
    @Autowired
    IProdutoEstoqueRepository produtoEstoqueRepository;

    private UUID generateUUID(){
        return UUID.randomUUID();
    }

    public List<OrdemDeServico> listarOrdensDeServico(){
        return ordemDeServicoRepository.findAll();
    }

    public OrdemDeServico cadastrar(CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        Oficina oficina = oficinaRepository.findById(novaOrdemDeServicoDTO.fkOficina()).orElseThrow(()-> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + novaOrdemDeServicoDTO.fkOficina()));
        Veiculo veiculo = veiculoRepository.findById(novaOrdemDeServicoDTO.fkVeiculo()).orElseThrow(()-> new RecursoNaoEncontradoException("Veículo não encontrado com o id: " + novaOrdemDeServicoDTO.fkVeiculo()));
        Mecanico mecanico = mecanicoRepository.findById(novaOrdemDeServicoDTO.fkMecanico()).orElseThrow(()-> new RecursoNaoEncontradoException("Mecânico não encontrado com o id: " + novaOrdemDeServicoDTO.fkMecanico()));
        ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findById(novaOrdemDeServicoDTO.fkProdutoEstoque()).orElseThrow(()-> new RecursoNaoEncontradoException("Produto de estoque não encontrado com o id: " + novaOrdemDeServicoDTO.fkProdutoEstoque()));
        OrdemDeServico ordemDeServico = new OrdemDeServico(novaOrdemDeServicoDTO, oficina, veiculo, mecanico, produtoEstoque);
        ordemDeServico.setUuid(generateUUID());
        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }

    public OrdemDeServico buscarPorId(Integer id){
        return ordemDeServicoRepository.findById(id).orElseThrow(()-> new RuntimeException("Ordem de serviço não encontrada com o id: " + id));
    }

    public void deletar(Integer id){
        OrdemDeServico ordemDeServico = buscarPorId(id);
        ordemDeServicoRepository.delete(ordemDeServico);
    }

    public OrdemDeServico atualizar(Integer id, CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        OrdemDeServico ordemDeServico = buscarPorId(id);
        ordemDeServico.setObservacoes(novaOrdemDeServicoDTO.descricao());
        ordemDeServico.setStatus(novaOrdemDeServicoDTO.status());
        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }
}
