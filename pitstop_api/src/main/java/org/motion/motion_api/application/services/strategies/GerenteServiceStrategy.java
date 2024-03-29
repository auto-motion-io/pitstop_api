package org.motion.motion_api.application.services.strategies;


import org.motion.motion_api.application.dtos.gerente.CreateGerenteDTO;
import org.motion.motion_api.application.dtos.gerente.LoginGerenteRequest;
import org.motion.motion_api.application.dtos.gerente.UpdateGerenteDTO;
import org.motion.motion_api.application.dtos.gerente.UpdateSenhaGerenteDTO;
import org.motion.motion_api.domain.entities.pitstop.Gerente;

public interface GerenteServiceStrategy extends BaseService<Gerente, UpdateGerenteDTO, CreateGerenteDTO>  {
    Gerente atualizarSenha(int id, UpdateSenhaGerenteDTO updateSenhaGerenteDTO);
    Gerente login(LoginGerenteRequest request);

}
