package org.example.pitstop_api.domain.repositories.pitstop;

import org.example.pitstop_api.domain.entities.pitstop.OrdemDeServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdemDeServicoRepository extends JpaRepository<OrdemDeServico,Integer> {
}
