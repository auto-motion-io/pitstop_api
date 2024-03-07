package org.example.pitstop_api.domain.repositories.buscar;

import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.buscar.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAvaliacaoRepository extends JpaRepository<Avaliacao,Integer> {
}
