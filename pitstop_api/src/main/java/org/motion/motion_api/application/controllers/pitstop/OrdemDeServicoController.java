package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.motion.motion_api.application.dtos.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.application.services.OrdemDeServicoService;
import org.motion.motion_api.domain.entities.pitstop.OrdemDeServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordemDeServicos")
public class OrdemDeServicoController {

    @Autowired
    OrdemDeServicoService ordemDeServicoService;

    @Operation(summary = "Listar ordens de serviço")
    @GetMapping()
    public ResponseEntity<List<OrdemDeServico>> listarOrdensDeServico(){
        return ResponseEntity.status(200).body(ordemDeServicoService.listarOrdensDeServico());
    }

    @Operation(summary = "Cadastrar ordem de serviço")
    @PostMapping()
    public ResponseEntity<OrdemDeServico> cadastrar(@RequestBody CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        return ResponseEntity.status(201).body(ordemDeServicoService.cadastrar(novaOrdemDeServicoDTO));
    }

    @Operation(summary = "Buscar ordem de serviço por id")
    @GetMapping("/{id}")
    public ResponseEntity<OrdemDeServico> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(ordemDeServicoService.buscarPorId(id));
    }

    @Operation(summary = "Deletar ordem de serviço por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        ordemDeServicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Atualizar ordem de serviço por id")
    @PutMapping("/{id}")
    public ResponseEntity<OrdemDeServico> atualizar(@PathVariable Integer id, @RequestBody CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        return ResponseEntity.status(200).body(ordemDeServicoService.atualizar(id, novaOrdemDeServicoDTO));
    }

    @Operation(summary = "Download ordem de serviço em csv")
    @GetMapping("/download-csv/{id}")
    public ResponseEntity<byte[]> downloadCsvPorId(@PathVariable int id){
        byte[] bytes = ordemDeServicoService.downloadCsvPorId(id);
        return ResponseEntity.status(200).body(bytes);
    }
}
