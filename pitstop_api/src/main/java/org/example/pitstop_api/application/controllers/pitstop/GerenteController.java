package org.example.pitstop_api.application.controllers.pitstop;

import org.example.pitstop_api.application.dtos.GerenteDTO;
import org.example.pitstop_api.domain.entities.Gerente;
import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.repositories.pitstop.IGerenteRepository;
import org.example.pitstop_api.domain.repositories.IOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {
    @Autowired
    IGerenteRepository gerenteRepository;

    @Autowired
    IOficinaRepository oficinaRepository;
    @GetMapping()
    public ResponseEntity getAll(){
        List<Gerente> gerentes = gerenteRepository.findAll();
        if(gerentes.isEmpty()) return ResponseEntity.status(204).body("Nenhum gerente encontrado");
        return ResponseEntity.ok().body(gerentes);
    }

    public ResponseEntity getByEmail(String email){
        Gerente gerente= gerenteRepository.findGerenteByEmail(email);
        if(gerente == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).build();

    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody GerenteDTO gerenteDTO){

        int getByEmailStatus = getByEmail(gerenteDTO.email()).getStatusCode().value();
        Oficina oficina = getOficina(gerenteDTO.fkOficina());
        if(oficina==null)return ResponseEntity.badRequest().body("Oficina inválida");
        if(getByEmailStatus == 204){
            Gerente gerente = new Gerente(gerenteDTO,oficina);
            gerenteRepository.save(gerente);
            return ResponseEntity.status(201).build();
      }else if (getByEmailStatus == 200){
            return  ResponseEntity.status(400).body("Gerente com email já cadastrado");
        }
        return ResponseEntity.status(400).build();
    }


    public Oficina getOficina(Integer idOficina){
        Optional<Oficina> optionalOficina = oficinaRepository.findById(idOficina);
        if(optionalOficina.isPresent()){
            return optionalOficina.get();
        }
        return null;
    }
}
