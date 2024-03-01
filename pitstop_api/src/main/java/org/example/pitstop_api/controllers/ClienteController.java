package org.example.pitstop_api.controllers;


import jakarta.validation.constraints.Null;
import org.example.pitstop_api.domain.Cliente;
import org.example.pitstop_api.dto.RequestClienteDTO;
import org.example.pitstop_api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping()
    public ResponseEntity getAllClients(){
        List<Cliente> clientes = repository.findAll();
        if(clientes.isEmpty())return ResponseEntity.status(204).body("Nenhum cliente encontrado");
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/getByEmail")
    public ResponseEntity getClientLogin(@RequestParam String email){
        System.out.println(email);
        Cliente cliente = repository.findByEmail(email);
        return cliente == null? ResponseEntity.status(204).build() : ResponseEntity.ok(cliente);
    }
    @PostMapping
    public ResponseEntity postClient(@RequestBody @Validated RequestClienteDTO data){
        repository.save( new Cliente(data));
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        Optional<Cliente> client = repository.findById(id);

        if(client.isPresent()){
            repository.delete(client.get());
            return ResponseEntity.status(200).body("Cliente deletado");
        }
            return ResponseEntity.status(404).body("Cliente não encontrado");

    }
}
