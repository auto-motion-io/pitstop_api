package org.motion.motion_api.application.controllers.buscar;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.motion.motion_api.application.services.ServicoBuscarService;
import org.motion.motion_api.domain.dtos.buscar.CreateServicoBuscarDTO;
import org.motion.motion_api.domain.entities.buscar.ServicoBuscar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buscar-servicos")
@SecurityRequirement(name = "motion_jwt")
public class ServicoBuscarController {

    @Autowired
    private ServicoBuscarService servicoBuscarService;


    @GetMapping()
    public ResponseEntity<List<ServicoBuscar>> listarServicos() {
        return ResponseEntity.ok(servicoBuscarService.listarServicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoBuscar> buscarServicoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicoBuscarService.buscarServicoPorId(id));
    }

    @PostMapping()
    public ResponseEntity<ServicoBuscar> cadastrar(@RequestBody @Valid CreateServicoBuscarDTO novoServicoBuscar) {
        return ResponseEntity.ok(servicoBuscarService.cadastrar(novoServicoBuscar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer id) {
        servicoBuscarService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}
