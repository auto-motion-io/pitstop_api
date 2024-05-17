package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.motion.motion_api.domain.dtos.pitstop.financeiro.CreateFinanceiroDTO;
import org.motion.motion_api.domain.dtos.pitstop.financeiro.ResponseDataFinanceiro;
import org.motion.motion_api.application.services.FinanceiroService;
import org.motion.motion_api.domain.entities.pitstop.Financeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeiro")
@SecurityRequirement(name = "motion_jwt")
public class FinanceiroController {
    @Autowired
    private FinanceiroService financeiroService;
    //crie o @Operation que defina o método abaixo e se puder cria o de todos os métodos dessa classe também
    @Operation(summary = "Lista as informações financeiras de uma oficina, saldo, valor de entradas, valor de saídas.")
    @GetMapping("/info/{id}")
    public ResponseEntity<ResponseDataFinanceiro> listarInformacaoByIdOficina(@PathVariable int id){
        ResponseDataFinanceiro info =  financeiroService.listarDadosFinanceiros(id);
        return ResponseEntity.status(200).body(info);
    }

    @Operation(summary = "Registra uma transação financeira.")
    @PostMapping()
    public ResponseEntity<Financeiro> registrarTransacaoFinanceira(@RequestBody CreateFinanceiroDTO dto){
        Financeiro financeiro = financeiroService.registrarTransacaoFinanceira(dto);
        return ResponseEntity.status(201).body(financeiro);
    }

    @Operation(summary = "Lista todas as operações financeiras de uma oficina.")
    @GetMapping("all/{id}")
    public ResponseEntity<List<Financeiro>> listarTodasOperacoesFinanceiras(@PathVariable int id){
        List<Financeiro> financas = financeiroService.listarTodasOperacoesFinanceiras(id);
        return ResponseEntity.status(200).body(financas);
    }

    @Operation(summary = "Deleta uma operação financeira.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFinanceiro(@PathVariable int id){
        financeiroService.deletarFinanceiro(id);
        return ResponseEntity.status(204).build();
    }

}
