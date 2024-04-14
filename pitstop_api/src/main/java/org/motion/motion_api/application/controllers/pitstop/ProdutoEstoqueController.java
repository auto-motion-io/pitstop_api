package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.motion.motion_api.application.dtos.produtoEstoque.CreateProdutoEstoqueDTO;
import org.motion.motion_api.application.services.ProdutoEstoqueService;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtoEstoque")
public class ProdutoEstoqueController {
    @Autowired
    private ProdutoEstoqueService produtoEstoqueService;

    @Operation(summary = "Retorna todos os produtos de estoque cadastrados.")
    @GetMapping()
    public ResponseEntity<List<ProdutoEstoque>> listarTodos() {
        return ResponseEntity.status(200).body(produtoEstoqueService.listarProdutosEstoque());
    }

    @Operation(summary = "Cadastra um produto de estoque")
    @PostMapping()
    public ResponseEntity<ProdutoEstoque> cadastrar(@RequestBody CreateProdutoEstoqueDTO createProdutoEstoqueDTO){
        ProdutoEstoque produtoEstoque = produtoEstoqueService.cadastrar(createProdutoEstoqueDTO);
        return ResponseEntity.status(201).body(produtoEstoque);
    }

    @Operation(summary = "Busca um produto de estoque por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEstoque> buscarPorId(@PathVariable int id) {
        ProdutoEstoque produtoEstoque = produtoEstoqueService.buscarPorId(id);
        return ResponseEntity.ok(produtoEstoque);
    }

    @Operation(summary = "Deleta um produto de estoque por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        produtoEstoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um produto de estoque por id")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEstoque> atualizar(@PathVariable int id, @RequestBody ProdutoEstoque produtoEstoque){
        ProdutoEstoque produtoEstoqueAtualizado = produtoEstoqueService.atualizar(id, produtoEstoque);
        return ResponseEntity.ok(produtoEstoqueAtualizado);
    }

    @Operation(summary = "Busca um produto de estoque por nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoEstoque> buscarPorNome(@PathVariable String nome){
        ProdutoEstoque produtoEstoque = produtoEstoqueService.buscarPorNome(nome);
        return ResponseEntity.ok(produtoEstoque);
    }

    @Operation(summary = "Busca um produto de estoque por oficina")
    @GetMapping("/oficina/{idOficina}")
    public ResponseEntity<ProdutoEstoque> buscarPorOficina(@PathVariable int idOficina){
        ProdutoEstoque produtoEstoque = produtoEstoqueService.buscarPorOficina(idOficina);
        return ResponseEntity.ok(produtoEstoque);
    }
}
