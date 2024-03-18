package org.motion.motion_api.application.services.strategies;


import org.motion.motion_api.application.dtos.CreateGerenteDTO;
import org.motion.motion_api.application.dtos.LoginGerenteRequest;
import org.motion.motion_api.application.dtos.UpdateGerenteDTO;
import org.motion.motion_api.application.dtos.UpdateSenhaGerenteDTO;
import org.motion.motion_api.domain.entities.pitstop.Gerente;

public interface GerenteServiceStrategy extends BaseService<Gerente, UpdateGerenteDTO, CreateGerenteDTO>  {
    Gerente atualizarSenha(int id, UpdateSenhaGerenteDTO updateSenhaGerenteDTO);
    Gerente login(LoginGerenteRequest request);

}
