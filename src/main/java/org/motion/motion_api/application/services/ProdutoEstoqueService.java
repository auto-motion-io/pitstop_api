package org.motion.motion_api.application.services;

import org.motion.motion_api.domain.dtos.pitstop.produtoEstoque.CreateProdutoEstoqueDTO;
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
    
    public List<ProdutoEstoque> listarProdutosEstoque(){
        return produtoEstoqueRepository.findAll();
    }
    
    public ProdutoEstoque cadastrar(CreateProdutoEstoqueDTO createProdutoEstoqueDTO){
        ProdutoEstoque produtoEstoque = new ProdutoEstoque(createProdutoEstoqueDTO, oficinaRepository.findById(createProdutoEstoqueDTO.fkOficina()).orElseThrow(()-> new RuntimeException("Oficina não encontrada com o id: " + createProdutoEstoqueDTO.fkOficina())));
        produtoEstoqueRepository.save(produtoEstoque);
        return produtoEstoque;
    }
    
    public ProdutoEstoque buscarPorId(Integer id){
        return produtoEstoqueRepository.findById(id).orElseThrow(()-> new RuntimeException("Produto de estoque não encontrado com o id: " + id));
    }
    
    public void deletar(Integer id){
        ProdutoEstoque produtoEstoque = buscarPorId(id);
        produtoEstoqueRepository.delete(produtoEstoque);
    }
    
    public ProdutoEstoque atualizar(Integer id, ProdutoEstoque produtoEstoque){
        ProdutoEstoque produtoEstoqueAtualizado = buscarPorId(id);
        produtoEstoqueAtualizado.setNome(produtoEstoque.getNome());
        produtoEstoqueAtualizado.setQuantidade(produtoEstoque.getQuantidade());
        produtoEstoqueAtualizado.setValorVenda(produtoEstoque.getValorVenda());
        produtoEstoqueRepository.save(produtoEstoqueAtualizado);
        return produtoEstoqueAtualizado;
    }
    
    public ProdutoEstoque buscarPorNome(String nome){
        return produtoEstoqueRepository.findByNome(nome).stream().findFirst().orElseThrow(()-> new RuntimeException("Produto de estoque não encontrado com o nome: " + nome));
    }

    public ProdutoEstoque buscarPorOficina(Integer idOficina){
        return produtoEstoqueRepository.findByOficina(oficinaRepository.findById(idOficina).orElseThrow(()-> new RuntimeException("Oficina não encontrada com o id: " + idOficina))).stream().findFirst().orElseThrow(()-> new RuntimeException("Produto de estoque não encontrado para a oficina com o id: " + idOficina));
    }
}
