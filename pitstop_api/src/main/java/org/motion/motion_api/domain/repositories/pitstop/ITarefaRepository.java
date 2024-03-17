package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.pitstop.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITarefaRepository extends JpaRepository<Tarefa,Integer> {
}
