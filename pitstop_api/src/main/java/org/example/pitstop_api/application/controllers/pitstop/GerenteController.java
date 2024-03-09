package org.example.pitstop_api.application.controllers.pitstop;


import jakarta.validation.Valid;
import org.example.pitstop_api.application.dtos.CreateGerenteDTO;
import org.example.pitstop_api.application.dtos.LoginGerenteRequest;
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
    public ResponseEntity<List<Gerente>> listarGerentes(){
        return ResponseEntity.status(200).body(gerenteService.listarGerentes());
    }

    @PostMapping("/login")
    public ResponseEntity<Gerente> login(@RequestBody @Valid LoginGerenteRequest request){
        Gerente gerente = gerenteService.login(request);
        return ResponseEntity.status(200).body(gerente);
    }

    @PostMapping()
    public ResponseEntity<Gerente> cadastrarGerente(@RequestBody CreateGerenteDTO createGerenteDTO){
        Gerente gerente = gerenteService.cadastrarGerente(createGerenteDTO);
        return ResponseEntity.status(201).body(gerente);
    }
}
