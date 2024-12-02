package org.motion.motion_api.application.services;

import jakarta.transaction.Transactional;
import org.motion.motion_api.domain.dtos.pitstop.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.domain.dtos.pitstop.ordemDeServico.OrdensPendentesUltimaSemanaDTO;
import org.motion.motion_api.domain.dtos.pitstop.ordemDeServico.OrdensUltimoAnoDTO;
import org.motion.motion_api.domain.dtos.pitstop.ordemDeServico.UpdateOrdemDeServicoDTO;
import org.motion.motion_api.application.exceptions.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.dtos.pitstop.produtoEstoque.ProdutoOrdemDTO;
import org.motion.motion_api.domain.dtos.pitstop.servico.ServicoOrdemDTO;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class
OrdemDeServicoService {

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

    public List<OrdemDeServico> listarOrdensDeServicoPorOficina(Integer idOficina) {
        Oficina oficina = oficinaRepository.findById(idOficina).
                orElseThrow(() -> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + idOficina));
        return ordemDeServicoRepository.findAllByOficina(oficina);
    }


    public List<OrdemDeServico> listarOrdensDeServicoPorCliente(String email) {
        return ordemDeServicoRepository.findAllByVeiculo_Cliente_Email(email);
    }

    @Transactional
    public OrdemDeServico cadastrar(CreateOrdemDeServicoDTO createOrdemDeServicoDTO) {
        OrdemDeServico ordemDeServico = new OrdemDeServico();
        ordemDeServico.setStatus(createOrdemDeServicoDTO.status());
        ordemDeServico.setGarantia(createOrdemDeServicoDTO.garantia());
        ordemDeServico.setToken(UUID.randomUUID().toString().substring(29, 36).toUpperCase());
        Oficina oficina = serviceHelper.pegarOficinaValida(createOrdemDeServicoDTO.fkOficina());

        ordemDeServico.setOficina(oficina);

        System.out.println(createOrdemDeServicoDTO.fkOficina());

        Veiculo veiculo = veiculoRepository.findById(createOrdemDeServicoDTO.fkVeiculo()).orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado com o id: " + createOrdemDeServicoDTO.fkVeiculo()));
        ordemDeServico.setVeiculo(veiculo);

        if(createOrdemDeServicoDTO.fkMecanico() != null){
            Mecanico mecanico = mecanicoRepository.findById(createOrdemDeServicoDTO.fkMecanico()).orElse(null);
            ordemDeServico.setMecanico(mecanico);
        }


        ordemDeServico.setDataInicio(createOrdemDeServicoDTO.dataInicio());
        ordemDeServico.setDataFim(createOrdemDeServicoDTO.dataFim());
        ordemDeServico.setTipoOs(createOrdemDeServicoDTO.tipoOs());


       List<ProdutoEstoque> produtosEstoque = new ArrayList<>();
       for(ProdutoOrdemDTO produtoOrdemDTO : createOrdemDeServicoDTO.produtos()) {
           ProdutoEstoque produto = produtoEstoqueRepository.findByNome(produtoOrdemDTO.nome());
           produtosEstoque.add(produto);

           if (produto.getQuantidade() < produtoOrdemDTO.quantidade()) {
               throw new RuntimeException("Quantidade de produto em estoque insuficiente para a ordem de serviço");
           }

           produto.setQuantidade(produto.getQuantidade() - produtoOrdemDTO.quantidade());
           produtoEstoqueRepository.save(produto);
           produtosEstoque.add(produto);
       }
        ordemDeServico.setProdutos(produtosEstoque);

        List<Servico> servico = servicoRepository.findByNomeIn(createOrdemDeServicoDTO
                .servicos()
                .stream()
                .map(ServicoOrdemDTO::nome)
                .collect(Collectors.toList()));
        ordemDeServico.setServicos(servico);

        ordemDeServico.setObservacoes(createOrdemDeServicoDTO.observacoes());
        ordemDeServico.setValorTotal(createOrdemDeServicoDTO.valorTotal());

        ordemDeServicoRepository.save(ordemDeServico);
        return ordemDeServico;
    }

    public OrdemDeServico buscarPorId(Integer id) {
        return ordemDeServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada com o id: " + id));
    }

    public void deletar(Integer id) {
        OrdemDeServico ordemDeServico = buscarPorId(id);

        ordemDeServico.getProdutos().clear();
        ordemDeServico.getServicos().clear();
        ordemDeServicoRepository.save(ordemDeServico);
        ordemDeServicoRepository.delete(ordemDeServico);
    }

    public FileSystemResource downloadCsvPorId(int id) throws IOException {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada com o id: " + id));

        String[] headers = {"id", "status", "garantia", "token", "fkOficina", "fkVeiculo", "fkMecanico", "dataInicio", "dataFim", "tipoOs", "produtos", "servicos", "observacoes"};

        // Matriz para armazenar os dados do CSV
        String[][] data = new String[2][headers.length];

        // Adicionar os headers na matriz
        for (int i = 0; i < headers.length; i++) {
            data[0][i] = headers[i];
        }

        // Adicionar os dados da ordem na matriz
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data[1][0] = ordem.getId() != null ? ordem.getId().toString() : "";
        data[1][1] = ordem.getStatus() != null ? ordem.getStatus() : "";
        data[1][2] = ordem.getGarantia() != null ? ordem.getGarantia() : "";
        data[1][3] = ordem.getToken() != null ? ordem.getToken() : "";
        data[1][4] = ordem.getOficina() != null && ordem.getOficina().getId() != null ? ordem.getOficina().getId().toString() : "";
        data[1][5] = ordem.getVeiculo() != null && ordem.getVeiculo().getId() != null ? ordem.getVeiculo().getId().toString() : "";
        data[1][6] = ordem.getMecanico() != null && ordem.getMecanico().getId() != null ? ordem.getMecanico().getId().toString() : "";
        data[1][7] = ordem.getDataInicio() != null ? ordem.getDataInicio().format(formatter) : "";
        data[1][8] = ordem.getDataFim() != null ? ordem.getDataFim().format(formatter) : "";
        data[1][9] = ordem.getTipoOs() != null ? ordem.getTipoOs() : "";
        data[1][10] = ordem.getProdutos() != null ? ordem.getProdutos().stream().map(ProdutoEstoque::getNome).collect(Collectors.joining(", ")) : "";
        data[1][11] = ordem.getServicos() != null ? ordem.getServicos().stream().map(Servico::getNome).collect(Collectors.joining(", ")) : "";
        data[1][12] = ordem.getObservacoes() != null ? ordem.getObservacoes() : "";

        // Fila para gerenciar as linhas do CSV
        Queue<String> csvLines = new ArrayDeque<>();

        // Adicionar os headers na fila
        var sj1 = new StringJoiner(";");
        for (String header : headers) {
            sj1.add(header);
        }
        csvLines.add(sj1.toString());

        // Adicionar os dados da ordem na fila
        var sj2 = new StringJoiner(";");
        for (String value : data[1]) {
            sj2.add(value);
        }
        csvLines.add(sj2.toString());

        // Construir o CSV usando a fila
        var sb = new StringBuilder();
        while (!csvLines.isEmpty()) {
            sb.append(csvLines.poll()).append("\n");
        }

        // Escrever o CSV no arquivo
        FileWriter file = new FileWriter("report.csv");
        file.write(sb.toString());
        file.close();
        var fileResource = new FileSystemResource("report.csv");

        MediaType mediaType = MediaTypeFactory
                .getMediaType(fileResource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);

        ContentDisposition disposition = ContentDisposition
                .attachment()
                .filename(fileResource.getFilename())
                .build();
        httpHeaders.setContentDisposition(disposition);

        return fileResource;
    }

    public OrdemDeServico buscarPorToken(String token) {
        if (ordemDeServicoRepository.findByToken(token) == null)
            throw new RecursoNaoEncontradoException("Ordem de serviço não encontrada com o token: " + token);
        return ordemDeServicoRepository.findByToken(token);
    }


    public List<OrdensPendentesUltimaSemanaDTO> quantidadeOrdensPendentes(Integer idOficina) {
        List<OrdensPendentesUltimaSemanaDTO> ordensPendentesUltimaSemana = new ArrayList<>();
        Oficina oficina = serviceHelper.pegarOficinaValida(idOficina);
        for (int i = 0; i < 7; i++) {
            LocalDate data = LocalDate.now().minusDays(i);
            Integer qtd = ordemDeServicoRepository.countByDataInicioAndStatusEqualsIgnoreCaseAndOficina(data, "PENDENTE",oficina);
            String diaSemana = data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            ordensPendentesUltimaSemana.add(new OrdensPendentesUltimaSemanaDTO(qtd, diaSemana));
        }
        return ordensPendentesUltimaSemana;
    }

    public List<OrdensUltimoAnoDTO> quantidadeOrdensMes(Integer idOficina) {
        List<OrdensUltimoAnoDTO> ordensUltimoAno = new ArrayList<>();
        Oficina oficina = serviceHelper.pegarOficinaValida(idOficina);
        for (int i = 0; i < 12; i++) {
            LocalDate dataInicio = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());

            Integer qtd = ordemDeServicoRepository.countByDataInicioBetweenAndOficina(dataInicio, dataFim, oficina);

            String mes = dataInicio.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            ordensUltimoAno.add(new OrdensUltimoAnoDTO(qtd, mes));
        }
        return ordensUltimoAno;
    }

    public List<ProdutoEstoque> addProdutoEstoqueAtOrdemDeServico(Integer idOrdemDeServico, List<ProdutoOrdemDTO> produtos) {
        OrdemDeServico ordemDeServico = ordemDeServicoRepository.findById(idOrdemDeServico)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada com o id: " + idOrdemDeServico));

        List<ProdutoEstoque> produtoEstoque = produtoEstoqueRepository.findByNomeIn(produtos
                .stream()
                .map(ProdutoOrdemDTO::nome)
                .collect(Collectors.toList()));

        ordemDeServico.getProdutos().addAll(produtoEstoque);
        ordemDeServicoRepository.save(ordemDeServico);
        return produtoEstoque;
    }
}