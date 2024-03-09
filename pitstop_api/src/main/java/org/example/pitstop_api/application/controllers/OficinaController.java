package org.example.pitstop_api.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import jakarta.validation.Valid;
import org.example.pitstop_api.application.dtos.UpdateOficinaDTO;
import org.example.pitstop_api.application.services.OficinaService;
import org.example.pitstop_api.domain.entities.Oficina;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/oficinas")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;


    @Operation(summary = "Busca todas oficinas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna as oficinas encontradas"),
            @ApiResponse(responseCode = "204", description = "Executou mas nenhuma oficina encontrada"),
            @ApiResponse(responseCode = "500", description = "back deixou passar")
    })
    @GetMapping
    public ResponseEntity<List<Oficina>> listarOficinas() {
        List<Oficina> oficinas = oficinaService.listarOficinas();
        if(oficinas.isEmpty())return ResponseEntity.status(204).build();
        return ResponseEntity.ok(oficinas);
    }



    @Operation(summary = "Busca uma oficina por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a oficina encontrada"),
            @ApiResponse(responseCode = "404", description = "Não encontrou a oficina com o id buscado"),
            @ApiResponse(responseCode = "500", description = "back deixou passar")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Oficina> buscarOficinaPorId(@PathVariable Integer id) {
        Oficina oficina = oficinaService.buscarOficinaPorId(id);
        return ResponseEntity.ok(oficina);
    }


    @Operation(summary = "Cadastra uma oficina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastra e retorna a oficina"),
            @ApiResponse(responseCode = "409", description = "CNPJ Conflitante"),
            @ApiResponse(responseCode = "500", description = "back deixou passar")
    })
    @PostMapping
    public ResponseEntity<Oficina> criarOficina(@RequestBody Oficina oficina) {
        Oficina novaOficina = oficinaService.criarOficina(oficina);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOficina);
    }

    @Operation(summary = "Atualiza os dados de uma oficina")
    @PutMapping("/{id}")
    public ResponseEntity<Oficina> atualizarOficina(@PathVariable Integer id, @Valid @RequestBody UpdateOficinaDTO oficinaAtualizada) {
        Oficina oficina = oficinaService.atualizarOficina(id, oficinaAtualizada);
        return ResponseEntity.ok(oficina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOficina(@PathVariable Integer id) {
        oficinaService.deletarOficina(id);
        return ResponseEntity.noContent().build();
    }
}
