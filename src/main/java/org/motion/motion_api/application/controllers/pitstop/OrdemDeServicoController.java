package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.motion.motion_api.domain.dtos.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.domain.dtos.ordemDeServico.UpdateOrdemDeServicoDTO;
import org.motion.motion_api.application.services.OrdemDeServicoService;
import org.motion.motion_api.domain.entities.pitstop.OrdemDeServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ordemDeServicos")
@SecurityRequirement(name = "motion_jwt")
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
    public ResponseEntity<OrdemDeServico> atualizar(@PathVariable Integer id, @RequestBody UpdateOrdemDeServicoDTO alterarOrdemDeServicoDTO){
        return ResponseEntity.status(200).body(ordemDeServicoService.atualizar(id, alterarOrdemDeServicoDTO));
    }

    @Operation(summary = "Download ordem de serviço em csv")
    @GetMapping("/download-csv/{id}")
    public ResponseEntity<FileSystemResource> downloadCsvPorId(@PathVariable int id) throws IOException {
        FileSystemResource fileResource = ordemDeServicoService.downloadCsvPorId(id);
        MediaType mediaType = MediaTypeFactory
                .getMediaType(fileResource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);

        ContentDisposition disposition = ContentDisposition
                // 3.2
                .attachment() // or .attachment()
                // 3.1
                .filename(fileResource.getFilename())
                .build();
        httpHeaders.setContentDisposition(disposition);

        return new ResponseEntity<>(fileResource, httpHeaders, HttpStatus.OK);

    }
}
