package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.motion.motion_api.application.dtos.servico.CreateServicoDTO;
import org.motion.motion_api.application.services.ServicoService;
import org.motion.motion_api.domain.entities.pitstop.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Operation(summary = "Retorna todos os serviços cadastrados.")
    @GetMapping()
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> servicos = servicoService.listarServicos();
        return ResponseEntity.status(200).body(servicos);
    }

    @Operation(summary = "Busca um serviço por id")
    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable int id) {
        Servico servico = servicoService.buscarPorId(id);
        return ResponseEntity.ok(servico);
    }

    @Operation(summary = "Cadastra um serviço")
    @PostMapping()
    public ResponseEntity<Servico> cadastrar(@RequestBody CreateServicoDTO createServicoDTO){
        Servico servico = servicoService.cadastrar(createServicoDTO);
        return ResponseEntity.status(201).body(servico);
    }

    @Operation(summary = "Deleta um serviço por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um serviço por id")
    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable int id, @RequestBody Servico servico){
        Servico servicoAtualizado = servicoService.atualizar(id, servico);
        return ResponseEntity.ok(servicoAtualizado);
    }
}
