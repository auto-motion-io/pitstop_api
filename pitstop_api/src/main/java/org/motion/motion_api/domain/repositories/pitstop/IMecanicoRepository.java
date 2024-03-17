package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.pitstop.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMecanicoRepository extends JpaRepository<Mecanico,Integer> {
}
