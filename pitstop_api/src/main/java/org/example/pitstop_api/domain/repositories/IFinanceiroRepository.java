package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Financeiro;
import org.example.pitstop_api.domain.entities.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinanceiroRepository extends JpaRepository<Financeiro,Integer> {
}
