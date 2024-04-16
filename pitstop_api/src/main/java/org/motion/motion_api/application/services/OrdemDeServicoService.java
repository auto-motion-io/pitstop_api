package org.motion.motion_api.application.services;

import org.motion.motion_api.application.dtos.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.application.exception.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.*;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
        ordemDeServico.setToken(UUID.randomUUID().toString().substring(31, 36));

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

    public OrdemDeServico atualizar(Integer id, CreateOrdemDeServicoDTO novaOrdemDeServicoDTO) {
        OrdemDeServico ordemDeServico = buscarPorId(id);
        ordemDeServico.setObservacoes(novaOrdemDeServicoDTO.observacoes());
        ordemDeServico.setStatus(novaOrdemDeServicoDTO.status());
        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }

    public byte[] downloadCsvPorId(int id){
        OrdemDeServico ordem = ordemDeServicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada com o id: " + id));

        String filePath = "ordem_" + id + ".csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("IdOrdem,DataInicio,DataFim,Status,Desconto,ValorTotal,Token,TipoOs,Garantia,Observacoes,Oficina,Veiculo,Mecanico,Produtos,Servicos\n");
            writer.append(String.valueOf(ordem.getIdOrdem())).append(",")
                    .append(String.valueOf(ordem.getDataInicio())).append(",")
                    .append(String.valueOf(ordem.getDataFim())).append(",")
                    .append(ordem.getStatus()).append(",")
                    .append(String.valueOf(ordem.getDesconto())).append(",")
                    .append(String.valueOf(ordem.getValorTotal())).append(",")
                    .append(ordem.getToken()).append(",")
                    .append(ordem.getTipoOs()).append(",")
                    .append(ordem.getGarantia()).append(",")
                    .append(ordem.getObservacoes()).append(",")
                    .append(ordem.getOficina().getNome()).append(",")
                    .append(ordem.getVeiculo().getModelo()).append(",")
                    .append(ordem.getMecanico() != null ? ordem.getMecanico().getNome() : "").append(",")
                    .append(ordem.getProdutos().stream().map(ProdutoEstoque::getNome).collect(Collectors.joining(";"))).append(",")
                    .append(ordem.getServicos().stream().map(Servico::getNome).collect(Collectors.joining(";")))
                    .append("\n");
        } catch (IOException e) {
            // handle exception
        }

        byte[] csvBytes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            csvBytes = sb.toString().getBytes();
        } catch (IOException e) {
            // handle exception
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "ordem_" + id + ".csv");

        return csvBytes;
    }
}
