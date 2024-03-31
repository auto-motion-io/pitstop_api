package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.motion.motion_api.application.dtos.CreateMecanicoDTO;
import org.motion.motion_api.application.services.MecanicoService;
import org.motion.motion_api.domain.entities.pitstop.Mecanico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mecanicos")
public class MecanicoController {

    @Autowired
    private MecanicoService mecanicoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o mecânico encontrado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou o mecânico com o id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Retorna todos os mecânicos cadastrados.")
    @GetMapping()
    public ResponseEntity<List<Mecanico>> listarTodos() {
        return ResponseEntity.status(200).body(mecanicoService.listarMecanicos());
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna o mecânico cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Cadastra um mecânico")
    @PostMapping()
    public ResponseEntity<Mecanico> cadastrar(@RequestBody CreateMecanicoDTO createMecanicoDTO){
        Mecanico mecanico = mecanicoService.cadastrar(createMecanicoDTO);
        return ResponseEntity.status(201).body(mecanico);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o mecânico encontrado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou o mecânico com o id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Busca um mecânico por id")
    @GetMapping("/{id}")
    public ResponseEntity<Mecanico> buscarPorId(@PathVariable int id) {
        Mecanico mecanico = mecanicoService.buscarPorId(id);
        return ResponseEntity.ok(mecanico);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mecânico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrou o mecânico com o id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Deleta um mecânico por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        mecanicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o mecânico atualizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou o mecânico com o id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Atualiza um mecânico por id")
    @PutMapping("/{id}")
    public ResponseEntity<Mecanico> atualizar(@PathVariable int id, @RequestBody Mecanico mecanico){
        Mecanico mecanicoAtualizado = mecanicoService.atualizar(id, mecanico);
        return ResponseEntity.ok(mecanicoAtualizado);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o mecânico encontrado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou o mecânico com o nome buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Busca um mecânico por nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Mecanico> buscarPorNome(@PathVariable String nome){
        Mecanico mecanico = mecanicoService.buscarPorNome(nome);
        return ResponseEntity.ok(mecanico);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o mecânico encontrado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou o mecânico com o nome e id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Busca um mecânico por nome e id")
    @GetMapping("/nome/{nome}/id/{id}")
    public ResponseEntity<Mecanico> buscarPorNomeEId(@PathVariable String nome, @PathVariable Integer id){
        Mecanico mecanico = mecanicoService.buscarPorNomeEId(nome, id);
        return ResponseEntity.ok(mecanico);
    }
}
