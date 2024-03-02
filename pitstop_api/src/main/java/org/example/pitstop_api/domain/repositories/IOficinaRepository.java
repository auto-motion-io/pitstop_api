package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOficinaRepository extends JpaRepository<Oficina,Integer> {
}
