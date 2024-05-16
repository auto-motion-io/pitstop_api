package org.motion.motion_api.application.controllers;

import io.swagger.v3.oas.annotations.Operation;


import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.motion.motion_api.domain.dtos.oficina.UpdateFotoOficinaDTO;
import org.motion.motion_api.domain.dtos.oficina.UpdateOficinaDTO;
import org.motion.motion_api.application.services.OficinaService;
import org.motion.motion_api.domain.entities.Oficina;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/oficinas")
@SecurityRequirement(name = "motion_jwt")
@CrossOrigin(origins = "*")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;


    @Operation(summary = "Busca todas oficinas")
    @GetMapping
    public ResponseEntity<List<Oficina>> listarOficinas() {
        List<Oficina> oficinas = oficinaService.listarTodos();
        if(oficinas.isEmpty())return ResponseEntity.status(204).build();
        return ResponseEntity.ok(oficinas);
    }



    @Operation(summary = "Busca uma oficina por id")
    @GetMapping("/{id}")
    public ResponseEntity<Oficina> buscarOficinaPorId(@PathVariable Integer id) {
        Oficina oficina = oficinaService.buscarPorId(id);
        return ResponseEntity.ok(oficina);
    }


    @Operation(summary = "Cadastra uma oficina")
    @PostMapping
    public ResponseEntity<Oficina> criarOficina(@RequestBody @Valid Oficina oficina) {
        Oficina novaOficina = oficinaService.criar(oficina);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOficina);
    }

    @Operation(summary = "Atualiza os dados de uma oficina")
    @PutMapping("/{id}")
    public ResponseEntity<Oficina> atualizarOficina(@PathVariable Integer id, @Valid @RequestBody UpdateOficinaDTO oficinaAtualizada) {
        Oficina oficina = oficinaService.atualizar(id, oficinaAtualizada);
        return ResponseEntity.ok(oficina);
    }

    @Operation(summary = "Atualiza a logo de uma oficina")
    @PutMapping("/atualiza-foto/{id}")
    public ResponseEntity<Oficina> atualizarFotoOficina(@PathVariable Integer id, @Valid @RequestBody UpdateFotoOficinaDTO dto) {
        Oficina oficina = oficinaService.atualizarLogoUrl(id, dto);
        return ResponseEntity.ok(oficina);
    }

    @Operation(summary = "Deleta uma oficina e seus registros")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOficina(@PathVariable Integer id) {
       // return ResponseEntity.status(501).build();
        oficinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
