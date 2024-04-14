package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.application.dtos.produtoEstoque.CreateProdutoEstoqueDTO;
import org.motion.motion_api.application.dtos.servico.CreateServicoDTO;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.*;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static java.util.spi.ToolProvider.findFirst;

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
    @Autowired
    IServicoRepository servicoRepository;

    public List<OrdemDeServico> listarOrdensDeServico(){
        return ordemDeServicoRepository.findAll();
    }

    public OrdemDeServico cadastrar(CreateOrdemDeServicoDTO createOrdemDeServicoDTO) {
        OrdemDeServico ordemDeServico = new OrdemDeServico();
        ordemDeServico.setStatus(createOrdemDeServicoDTO.status());
        ordemDeServico.setGarantia(createOrdemDeServicoDTO.garantia());
        ordemDeServico.setToken(UUID.randomUUID().toString().substring(31,36));

        Veiculo veiculo = veiculoRepository.findById(createOrdemDeServicoDTO.fkVeiculo()).orElseThrow(()-> new RecursoNaoEncontradoException("Veículo não encontrado com o id: " + createOrdemDeServicoDTO.fkVeiculo()));
        ordemDeServico.setVeiculo(veiculo);

        Cliente cliente = veiculo.getCliente();
        ordemDeServico.setCliente(cliente);

        Mecanico mecanico = mecanicoRepository.findById(createOrdemDeServicoDTO.fkMecanico()).orElseThrow(()-> new RecursoNaoEncontradoException("Mecânico não encontrado com o id: " + createOrdemDeServicoDTO.fkMecanico()));
        ordemDeServico.setMecanico(mecanico);

        ordemDeServico.setDataInicio(createOrdemDeServicoDTO.dataInicio());
        ordemDeServico.setDataFim(createOrdemDeServicoDTO.dataFim());
        ordemDeServico.setTipoOs(createOrdemDeServicoDTO.tipoOs());

        ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findByNomeIn(createOrdemDeServicoDTO.produtos()).stream().findFirst().orElseThrow(()-> new RecursoNaoEncontradoException("Produto não encontrado com o nome: " + createOrdemDeServicoDTO.produtos().stream().findFirst().get()));
        ordemDeServico.getProdutos().add(produtoEstoque);

        Servico servico = servicoRepository.findByNomeIn(createOrdemDeServicoDTO.servicos()).stream().findFirst().orElseThrow(()-> new RecursoNaoEncontradoException("Serviço não encontrado com o nome: " + createOrdemDeServicoDTO.servicos().stream().findFirst().get()));
        ordemDeServico.getServicos().add(servico);

        ordemDeServico.setObservacoes(createOrdemDeServicoDTO.observacoes());
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
        ordemDeServico.setObservacoes(novaOrdemDeServicoDTO.observacoes());
        ordemDeServico.setStatus(novaOrdemDeServicoDTO.status());
        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }
}
