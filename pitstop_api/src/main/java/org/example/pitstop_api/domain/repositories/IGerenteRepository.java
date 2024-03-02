package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Gerente;
import org.example.pitstop_api.domain.entities.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGerenteRepository extends JpaRepository<Gerente,Integer> {
}
