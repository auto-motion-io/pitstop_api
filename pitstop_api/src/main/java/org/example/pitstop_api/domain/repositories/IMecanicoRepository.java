package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.pitstop.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMecanicoRepository extends JpaRepository<Mecanico,Integer> {
}
