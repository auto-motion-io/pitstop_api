package org.motion.motion_api.domain.repositories.buscar;

import org.motion.motion_api.domain.entities.buscar.ServicoBuscar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicoBuscarRepository extends JpaRepository<ServicoBuscar, Integer> {
}
