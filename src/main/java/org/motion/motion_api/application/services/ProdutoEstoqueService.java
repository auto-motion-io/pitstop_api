package org.motion.motion_api.application.services;

import org.motion.motion_api.application.exceptions.RecursoNaoEncontradoException;
import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.dtos.pitstop.produtoEstoque.CreateProdutoEstoqueDTO;
import org.motion.motion_api.domain.dtos.pitstop.produtoEstoque.UpdateProdutoEstoqueDTO;
import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.motion.motion_api.domain.repositories.IOficinaRepository;
import org.motion.motion_api.domain.repositories.pitstop.IOrdemDeServicoRepository;
import org.motion.motion_api.domain.repositories.pitstop.IProdutoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoEstoqueService {

    @Autowired
    IProdutoEstoqueRepository produtoEstoqueRepository;
    @Autowired
    IOficinaRepository oficinaRepository;
    @Autowired
    IOrdemDeServicoRepository ordemDeServicoRepository;
    @Autowired
    ServiceHelper serviceHelper;
    
    public List<ProdutoEstoque> listarProdutosEstoque(){
        return produtoEstoqueRepository.findAll();
    }
    
    public ProdutoEstoque cadastrar(CreateProdutoEstoqueDTO createProdutoEstoqueDTO){
        ProdutoEstoque produtoEstoque = new ProdutoEstoque(createProdutoEstoqueDTO, oficinaRepository.findById(createProdutoEstoqueDTO.fkOficina()).orElseThrow(()-> new RuntimeException("Oficina não encontrada com o id: " + createProdutoEstoqueDTO.fkOficina())));
        Oficina oficina = serviceHelper.pegarOficinaValida(createProdutoEstoqueDTO.fkOficina());
        produtoEstoque.setOficina(oficina);
        produtoEstoqueRepository.save(produtoEstoque);
        return produtoEstoque;
    }
    
    public ProdutoEstoque buscarPorId(Integer id){
        return produtoEstoqueRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Produto de estoque não encontrado com o id: " + id));
    }

    public List<ProdutoEstoque> listarPorPreco(double precoMinimo, double precoMaximo, String nome){
        return produtoEstoqueRepository.findByValorVendaBetweenAndNomeContainingIgnoreCase(precoMinimo, precoMaximo,nome);
    }
    
    public void deletar(Integer id){
        ProdutoEstoque produtoEstoque = buscarPorId(id);
        produtoEstoqueRepository.delete(produtoEstoque);
    }
    
    public ProdutoEstoque atualizar(Integer id, UpdateProdutoEstoqueDTO produtoEstoque){
        ProdutoEstoque produtoEstoqueAtualizado = buscarPorId(id);
        produtoEstoqueAtualizado.setNome(produtoEstoque.nome());
        produtoEstoqueAtualizado.setModeloVeiculo(produtoEstoque.modeloVeiculo());
        produtoEstoqueAtualizado.setQuantidade(produtoEstoque.quantidade());
        produtoEstoqueAtualizado.setLocalizacao(produtoEstoque.localizacao());
        produtoEstoqueAtualizado.setValorCompra(produtoEstoque.valorCompra());
        produtoEstoqueAtualizado.setValorVenda(produtoEstoque.valorVenda());
        produtoEstoqueAtualizado.setValorComMaoObra(produtoEstoque.valorComMaoObra());
        produtoEstoqueAtualizado.setGarantia(produtoEstoque.garantia());
        produtoEstoqueRepository.save(produtoEstoqueAtualizado);
        return produtoEstoqueAtualizado;
    }
    
    public ProdutoEstoque buscarPorNome(String nome){
        return produtoEstoqueRepository.findByNome(nome).stream().findFirst().orElseThrow(()-> new RecursoNaoEncontradoException("Produto de estoque não encontrado com o nome: " + nome));
    }

    public List<ProdutoEstoque> buscarPorOficina(Integer idOficina){
        return produtoEstoqueRepository.findByOficina(oficinaRepository.findById(idOficina).orElseThrow(()-> new RecursoNaoEncontradoException("Oficina não encontrada com o id: " + idOficina))).stream().toList();
    }

    public List<ProdutoEstoque> buscarEstoqueBaixo(Integer idOficina){
        Oficina oficina = serviceHelper.pegarOficinaValida(idOficina);
        return produtoEstoqueRepository.findByOficinaAndQuantidadeLessThanEqual(oficina, 10);
    }

}
