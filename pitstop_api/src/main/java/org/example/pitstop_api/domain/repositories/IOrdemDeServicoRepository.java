package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.OrdemDeServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdemDeServicoRepository extends JpaRepository<OrdemDeServico,Integer> {
}
