package org.example.pitstop_api.application.controllers.pitstop;


import io.swagger.v3.oas.annotations.Operation;
import org.example.pitstop_api.domain.entities.pitstop.Cliente;
import org.example.pitstop_api.application.dtos.RequestClienteDTO;
import org.example.pitstop_api.domain.repositories.pitstop.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

}
