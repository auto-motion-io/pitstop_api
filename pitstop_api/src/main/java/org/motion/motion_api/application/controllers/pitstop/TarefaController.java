package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.motion.motion_api.application.dtos.CreateTarefaDTO;
import org.motion.motion_api.application.dtos.UpdateTarefaDTO;
import org.motion.motion_api.application.services.TarefaService;
import org.motion.motion_api.domain.entities.pitstop.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;


    @Operation(summary = "Busca todas as tarefas, recebe o id da oficina buscada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna as tarefas encontradas"),
            @ApiResponse(responseCode = "204", description = "Não encontrou nenhuma tarefa"),
            @ApiResponse(responseCode = "404", description = "Não encontrou a oficina com o id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<Tarefa>> listarTodasTarefasPorIdOficina(@PathVariable int id) {
        List<Tarefa> tarefas = tarefaService.listarTodasTarefasPorIdOficina(id);
        return tarefas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(tarefas);
    }

    @Operation(summary = "Cadastra uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastra e retorna a tarefa cadastrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    public ResponseEntity<Tarefa> criar(@RequestBody CreateTarefaDTO createTarefaDTO) {
        return ResponseEntity.status(201).body(tarefaService.criar(createTarefaDTO));
    }


    @Operation(summary = "Atualiza o status de uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza e retorna a tarefa atualizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@RequestBody UpdateTarefaDTO updateTarefaDTO, @PathVariable int id) {
        return ResponseEntity.status(200).body(tarefaService.atualizar(id, updateTarefaDTO));
    }
}
