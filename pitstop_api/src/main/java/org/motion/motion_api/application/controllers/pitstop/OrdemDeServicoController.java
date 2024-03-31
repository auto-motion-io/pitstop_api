package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.motion.motion_api.application.dtos.CreateOrdemDeServicoDTO;
import org.motion.motion_api.application.services.OrdemDeServicoService;
import org.motion.motion_api.domain.entities.pitstop.OrdemDeServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordemDeServicos")
public class OrdemDeServicoController {

    @Autowired
    OrdemDeServicoService ordemDeServicoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de ordens de serviço realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Listar ordens de serviço")
    @GetMapping()
    public List<OrdemDeServico> listarOrdensDeServico(){
        return ordemDeServicoService.listarOrdensDeServico();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Cadastrar ordem de serviço")
    @PostMapping()
    public OrdemDeServico cadastrar(@RequestBody CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        return ordemDeServicoService.cadastrar(novaOrdemDeServicoDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Buscar ordem de serviço por id")
    @GetMapping("/{id}")
    public OrdemDeServico buscarPorId(@PathVariable Integer id){
        return ordemDeServicoService.buscarPorId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Deletar ordem de serviço por id")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        ordemDeServicoService.deletar(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @Operation(summary = "Atualizar ordem de serviço por id")
    @PutMapping("/{id}")
    public OrdemDeServico atualizar(@PathVariable Integer id, @RequestBody CreateOrdemDeServicoDTO novaOrdemDeServicoDTO){
        return ordemDeServicoService.atualizar(id, novaOrdemDeServicoDTO);
    }
}
