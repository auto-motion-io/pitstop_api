package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public List<OrdemDeServico> listarOrdensDeServico(){
        return ordemDeServicoService.listarOrdensDeServico();
    }

    @Operation(summary = "Cadastrar ordem de serviço")
    @PostMapping()
    public OrdemDeServico cadastrar(@RequestBody CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        return ordemDeServicoService.cadastrar(novaOrdemDeServicoDTO);
    }

    @Operation(summary = "Buscar ordem de serviço por id")
    @GetMapping("/{id}")
    public OrdemDeServico buscarPorId(@PathVariable Integer id){
        return ordemDeServicoService.buscarPorId(id);
    }

    @Operation(summary = "Deletar ordem de serviço por id")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        ordemDeServicoService.deletar(id);
    }


    @Operation(summary = "Atualizar ordem de serviço por id")
    @PutMapping("/{id}")
    public OrdemDeServico atualizar(@PathVariable Integer id, @RequestBody CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        return ordemDeServicoService.atualizar(id, novaOrdemDeServicoDTO);
    }

    @Operation(summary = "Download ordem de serviço em csv")
    @GetMapping("/download-csv/{id}")
    public ResponseEntity<byte[]> downloadCsvPorId(@PathVariable int id){
        byte[] bytes = ordemDeServicoService.downloadCsvPorId(id);
        return ResponseEntity.status(200).body(bytes);
    }
}
