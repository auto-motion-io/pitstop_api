package org.motion.motion_api.application.controllers.pitstop;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.motion.motion_api.application.dtos.gerente.CreateGerenteDTO;
import org.motion.motion_api.application.dtos.gerente.LoginGerenteRequest;
import org.motion.motion_api.application.dtos.gerente.UpdateGerenteDTO;
import org.motion.motion_api.application.dtos.gerente.UpdateSenhaGerenteDTO;
import org.motion.motion_api.application.services.GerenteService;
import org.motion.motion_api.application.services.authorization.AuthorizationService;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.motion.motion_api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    @Operation(summary = "Retorna todos os gerentes cadastrados.")
    @GetMapping()
    public ResponseEntity<List<Gerente>> listarTodos() {
        return ResponseEntity.status(200).body(gerenteService.listarTodos());
    }

    @Operation(summary = "Busca um gerente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o gerente encontrado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou a gerente com o id buscado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Gerente> buscarPorId(@PathVariable int id) {
        Gerente gerente = gerenteService.buscarPorId(id);
        return ResponseEntity.ok(gerente);
    }

    @Operation(summary = "Retorna um gerente a partir de um email e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o gerente caso o email e a senha passada sejam válidas."),
            @ApiResponse(responseCode = "401", description = "Caso encontre o email porém a senha não está correta"),
            @ApiResponse(responseCode = "404", description = "Caso não encontre o email passado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor :(")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginGerenteRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(),request.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Gerente) auth.getPrincipal());
        //Gerente gerente = gerenteService.login(request);
        return ResponseEntity.ok(token);
        //return ResponseEntity.status(200).body(gerente);
    }


    @Operation(summary = "Cadastra um gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cria e retorna um gerente"),
            @ApiResponse(responseCode = "409", description = "Caso o email já esteja cadastrado ou a oficina passada já possuir um gerente"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor :(")
    })
    @PostMapping()
    public ResponseEntity<Gerente> cadastrar(@RequestBody @Valid CreateGerenteDTO createGerenteDTO) {
        Gerente gerente = gerenteService.criar(createGerenteDTO);
        return ResponseEntity.status(201).body(gerente);
    }


    @Operation(summary = "Atualiza o nome e o sobrenome do gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza e retorna o gerente atualizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou gerente com id passado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor :(")
    })
    @PutMapping("{id}")
    public ResponseEntity<Gerente> atualizar(@PathVariable int id, @RequestBody UpdateGerenteDTO updateGerenteDTO) {
        Gerente gerente = gerenteService.atualizar(id, updateGerenteDTO);
        return ResponseEntity.status(200).body(gerente);
    }

    @Operation(summary = "Atualiza a senha do gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza e retorna o gerente atualizado"),
            @ApiResponse(responseCode = "404", description = "Não encontrou gerente com id passado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor :(")
    })
    @PutMapping("/atualiza-senha/{id}")
    public ResponseEntity<Gerente> atualizarSenha(@PathVariable int id, @RequestBody UpdateSenhaGerenteDTO updateSenhaGerenteDTO) {
        Gerente gerente = gerenteService.atualizarSenha(id, updateSenhaGerenteDTO);
        return ResponseEntity.status(200).body(gerente);
    }
}
