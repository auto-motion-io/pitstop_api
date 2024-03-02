package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Mecanico;
import org.example.pitstop_api.domain.entities.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMecanicoRepository extends JpaRepository<Mecanico,Integer> {
}
