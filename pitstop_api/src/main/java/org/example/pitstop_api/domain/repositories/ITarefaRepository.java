package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.pitstop.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITarefaRepository extends JpaRepository<Tarefa,Integer> {
}
