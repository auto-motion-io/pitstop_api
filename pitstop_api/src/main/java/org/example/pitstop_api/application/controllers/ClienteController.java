package org.example.pitstop_api.application.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.example.pitstop_api.domain.entities.pitstop.Cliente;
import org.example.pitstop_api.application.dtos.RequestClienteDTO;
import org.example.pitstop_api.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clientRepository;

    @GetMapping()
    public ResponseEntity getAllClients(){
        List<Cliente> clientes = clientRepository.findAll();
        if(clientes.isEmpty())return ResponseEntity.status(204).body("Nenhum cliente encontrado");
        return ResponseEntity.ok(clientes);
    }
    @Operation(description = "A partir de um email, caso esteja cadastrado, retorna um, caso não exista, retorna 204 No content")
    @GetMapping("/getByEmail")
    public ResponseEntity getClientLogin(@RequestParam String email){
        System.out.println(email);
        Cliente cliente = clientRepository.findByEmail(email);
        return cliente == null? ResponseEntity.status(204).build() : ResponseEntity.ok(cliente);
    }
    @PostMapping
    public ResponseEntity postClient(@RequestBody RequestClienteDTO data){
        clientRepository.save( new Cliente(data));
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id){
        Optional<Cliente> client = clientRepository.findById(id);

        if(client.isPresent()){
            clientRepository.delete(client.get());
            return ResponseEntity.status(200).body("Cliente deletado");
        }
            return ResponseEntity.status(404).body("Cliente não encontrado");

    }
}
