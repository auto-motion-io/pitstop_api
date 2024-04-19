package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITarefaRepository extends JpaRepository<Tarefa,Integer> {
    List<Tarefa> findByOficinaIdOficina(Integer fkOficina);
}
