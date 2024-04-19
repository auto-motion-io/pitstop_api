package org.motion.motion_api.application.controllers.pitstop;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financeiro")
@SecurityRequirement(name = "motion_jwt")
public class FinanceiroController {
}
