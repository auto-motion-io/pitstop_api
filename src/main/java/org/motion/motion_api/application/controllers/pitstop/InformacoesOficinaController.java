package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oficinas/info")
@SecurityRequirement(name = "motion_jwt")
public class InformacoesOficinaController {
        /*TODO
        * Buscar Todos
        * Buscar Por Id
        * Atualizar
        * */

}
