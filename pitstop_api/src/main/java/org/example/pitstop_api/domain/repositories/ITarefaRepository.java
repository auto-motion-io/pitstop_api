package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITarefaRepository extends JpaRepository<Tarefa,Integer> {
}
