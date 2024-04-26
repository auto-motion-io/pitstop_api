package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.motion.motion_api.application.dtos.financeiro.ResponseDataFinanceiro;
import org.motion.motion_api.domain.entities.pitstop.Financeiro;
import org.motion.motion_api.domain.repositories.pitstop.IFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financeiro")
@SecurityRequirement(name = "motion_jwt")
public class FinanceiroController {
    @GetMapping("/info/{id}")
    public ResponseEntity<ResponseDataFinanceiro> listarInformacaoByIdOficina(@PathVariable int id){
    return null;
    }
}
