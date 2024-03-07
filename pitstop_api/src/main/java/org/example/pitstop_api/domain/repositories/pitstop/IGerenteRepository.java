package org.example.pitstop_api.domain.repositories.pitstop;

import org.example.pitstop_api.domain.entities.pitstop.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGerenteRepository extends JpaRepository<Gerente,Integer> {
    public Gerente findGerenteByEmail(String email);
}
