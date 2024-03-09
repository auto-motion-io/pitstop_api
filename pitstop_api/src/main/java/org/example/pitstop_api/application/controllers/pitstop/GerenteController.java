package org.example.pitstop_api.application.controllers.pitstop;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.pitstop_api.application.dtos.CreateGerenteDTO;
import org.example.pitstop_api.application.dtos.LoginGerenteRequest;
import org.example.pitstop_api.application.dtos.UpdateGerenteDTO;
import org.example.pitstop_api.application.services.GerenteService;
import org.example.pitstop_api.domain.entities.pitstop.Gerente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;


    @GetMapping()
    public ResponseEntity<List<Gerente>> listarTodos(){
        return ResponseEntity.status(200).body(gerenteService.listarGerentes());
    }

    @PostMapping("/login")
    public ResponseEntity<Gerente> login(@RequestBody @Valid LoginGerenteRequest request){
        Gerente gerente = gerenteService.login(request);
        return ResponseEntity.status(200).body(gerente);
    }

    @PostMapping()
    public ResponseEntity<Gerente> cadastrar(@RequestBody CreateGerenteDTO createGerenteDTO){
        Gerente gerente = gerenteService.cadastrar(createGerenteDTO);
        return ResponseEntity.status(201).body(gerente);
    }


    @Operation(summary = "Atualiza o nome e o sobrenome do gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza e retorna o gerente atualizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou gerente com id passado"),
            @ApiResponse(responseCode = "500", description = "Back deixou passar")
    })
    @PutMapping("{id}")
    public ResponseEntity<Gerente> atualizar(@PathVariable int id, @RequestBody UpdateGerenteDTO updateGerenteDTO){
        Gerente gerente = gerenteService.atualizar(id,updateGerenteDTO);
        return ResponseEntity.status(200).body(gerente);
    }
}
