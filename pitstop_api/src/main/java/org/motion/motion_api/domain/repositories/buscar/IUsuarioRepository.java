package org.motion.motion_api.domain.repositories.buscar;

import org.motion.motion_api.domain.entities.buscar.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
}
