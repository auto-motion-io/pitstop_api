package org.motion.motion_api.application.controllers.pitstop;

import org.motion.motion_api.application.dtos.veiculo.CreateVeiculoDTO;
import org.motion.motion_api.application.services.VeiculoService;
import org.motion.motion_api.domain.entities.pitstop.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping()
    public ResponseEntity<List<Veiculo>> listarTodos(){
        return ResponseEntity.status(200).body(veiculoService.listarVeiculos());
    }

    @PostMapping()
    public ResponseEntity<Veiculo> cadastrar(CreateVeiculoDTO novoVeiculoDTO){
        Veiculo veiculo = veiculoService.cadastrar(novoVeiculoDTO);
        return ResponseEntity.status(201).body(veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        veiculoService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable int id, CreateVeiculoDTO novoVeiculoDTO){
        Veiculo veiculo = veiculoService.atualizar(id,novoVeiculoDTO);
        return ResponseEntity.status(200).body(veiculo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable int id){
        Veiculo veiculo = veiculoService.buscarPorId(id);
        return ResponseEntity.status(200).body(veiculo);
    }
}
