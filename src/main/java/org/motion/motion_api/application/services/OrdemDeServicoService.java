package org.motion.motion_api.application.services;

import org.motion.motion_api.domain.dtos.pitstop.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.domain.dtos.pitstop.ordemDeServico.UpdateOrdemDeServicoDTO;
import org.motion.motion_api.application.exceptions.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.*;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Autowired
    ServiceHelper serviceHelper;

    public List<OrdemDeServico> listarOrdensDeServico() {
        return ordemDeServicoRepository.findAll();
    }

    public OrdemDeServico cadastrar(CreateOrdemDeServicoDTO createOrdemDeServicoDTO) {
        OrdemDeServico ordemDeServico = new OrdemDeServico();
        ordemDeServico.setStatus(createOrdemDeServicoDTO.status());
        ordemDeServico.setGarantia(createOrdemDeServicoDTO.garantia());
        ordemDeServico.setToken(UUID.randomUUID().toString().substring(31, 36).toUpperCase());

        Oficina oficina = serviceHelper.pegarOficinaValida(createOrdemDeServicoDTO.fkOficina());

        ordemDeServico.setOficina(oficina);

        System.out.println(createOrdemDeServicoDTO.fkOficina());

        Veiculo veiculo = veiculoRepository.findById(createOrdemDeServicoDTO.fkVeiculo()).orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado com o id: " + createOrdemDeServicoDTO.fkVeiculo()));
        ordemDeServico.setVeiculo(veiculo);

        Mecanico mecanico = mecanicoRepository.findById(createOrdemDeServicoDTO.fkMecanico()).orElse(null);
        if (mecanico != null) {
            ordemDeServico.setMecanico(mecanico);
        }

        ordemDeServico.setDataInicio(createOrdemDeServicoDTO.dataInicio());
        ordemDeServico.setDataFim(createOrdemDeServicoDTO.dataFim());
        ordemDeServico.setTipoOs(createOrdemDeServicoDTO.tipoOs());

        List<ProdutoEstoque> produtoEstoque = produtoEstoqueRepository.findByNomeIn(createOrdemDeServicoDTO.produtos());
        ordemDeServico.setProdutos(produtoEstoque);

        List<Servico> servico = servicoRepository.findByNomeIn(createOrdemDeServicoDTO.servicos());
        ordemDeServico.setServicos(servico);

        ordemDeServico.setObservacoes(createOrdemDeServicoDTO.observacoes());
        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }

    public OrdemDeServico buscarPorId(Integer id) {
        return ordemDeServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada com o id: " + id));
    }

    public void deletar(Integer id) {
        OrdemDeServico ordemDeServico = buscarPorId(id);
        ordemDeServicoRepository.delete(ordemDeServico);
    }

    public OrdemDeServico atualizar(Integer id, UpdateOrdemDeServicoDTO alterarOrdemDeServicoDTO) {
        OrdemDeServico ordemDeServico = buscarPorId(id);
        ordemDeServico.setStatus(alterarOrdemDeServicoDTO.status());
        ordemDeServico.setGarantia(alterarOrdemDeServicoDTO.garantia());

        Oficina oficina = serviceHelper.pegarOficinaValida(alterarOrdemDeServicoDTO.fkOficina());
        ordemDeServico.setOficina(oficina);

        Veiculo veiculo = veiculoRepository.findById(alterarOrdemDeServicoDTO.fkVeiculo()).orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado com o id: " + alterarOrdemDeServicoDTO.fkVeiculo()));
        ordemDeServico.setVeiculo(veiculo);

        Mecanico mecanico = mecanicoRepository.findById(alterarOrdemDeServicoDTO.fkMecanico()).orElse(null);
        if (mecanico != null) {
            ordemDeServico.setMecanico(mecanico);
        }

        ordemDeServico.setDataInicio(alterarOrdemDeServicoDTO.dataInicio());
        ordemDeServico.setDataFim(alterarOrdemDeServicoDTO.dataFim());
        ordemDeServico.setTipoOs(alterarOrdemDeServicoDTO.tipoOs());

        List<ProdutoEstoque> produtoEstoque = produtoEstoqueRepository.findByNomeIn(alterarOrdemDeServicoDTO.produtos());
        ordemDeServico.setProdutos(produtoEstoque);

        List<Servico> servico = servicoRepository.findByNomeIn(alterarOrdemDeServicoDTO.servicos());
        ordemDeServico.setServicos(servico);

        ordemDeServico.setObservacoes(alterarOrdemDeServicoDTO.observacoes());
        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }

    public FileSystemResource downloadCsvPorId(int id) throws IOException {
        var sb = new StringBuilder();
        OrdemDeServico ordem = ordemDeServicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada com o id: " + id));


        String[] headers = {"id", "status", "garantia", "token", "fkOficina", "fkVeiculo", "fkMecanico", "dataInicio", "dataFim", "tipoOs", "produtos", "servicos", "observacoes"};

        var sj1 = new StringJoiner(";");
        for (String header : headers) {
            sj1.add(header);
        }


        sb.append(sj1 + "\n");

        var sj2 = new StringJoiner(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        sj2.add(ordem.getIdOrdem() != null ? ordem.getIdOrdem().toString() : "");
        sj2.add(ordem.getStatus() != null ? ordem.getStatus() : "");
        sj2.add(ordem.getGarantia() != null ? ordem.getGarantia() : "");
        sj2.add(ordem.getToken() != null ? ordem.getToken() : "");
        sj2.add(ordem.getOficina() != null && ordem.getOficina().getIdOficina() != null ? ordem.getOficina().getIdOficina().toString() : "");
        sj2.add(ordem.getVeiculo() != null && ordem.getVeiculo().getIdVeiculo() != null ? ordem.getVeiculo().getIdVeiculo().toString() : "");
        sj2.add(ordem.getMecanico() != null && ordem.getMecanico().getIdMecanico() != null ? ordem.getMecanico().getIdMecanico().toString() : "");
        sj2.add(ordem.getDataInicio() != null ? ordem.getDataInicio().format(formatter) : "");
        sj2.add(ordem.getDataFim() != null ? ordem.getDataFim().format(formatter) : "");
        sj2.add(ordem.getTipoOs() != null ? ordem.getTipoOs() : "");
        sj2.add(ordem.getProdutos() != null ? ordem.getProdutos().stream().map(ProdutoEstoque::getNome).collect(Collectors.joining(", ")) : "");
        sj2.add(ordem.getServicos() != null ? ordem.getServicos().stream().map(Servico::getNome).collect(Collectors.joining(", ")) : "");
        sj2.add(ordem.getObservacoes() != null ? ordem.getObservacoes() : "");


        sb.append(sj2 + "\n");

        FileWriter file = new FileWriter("report.csv");
        file.write(sb.toString());
        file.close();
        var fileResource = new FileSystemResource("report.csv");

        MediaType mediaType = MediaTypeFactory
                .getMediaType(fileResource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        // 3
        ContentDisposition disposition = ContentDisposition
                // 3.2
                .attachment() // or .attachment()
                // 3.1
                .filename(fileResource.getFilename())
                .build();
        httpHeaders.setContentDisposition(disposition);

        return fileResource;
    }
}