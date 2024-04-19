package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.pitstop.OrdemDeServico;
import org.motion.motion_api.domain.entities.pitstop.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrdemDeServicoRepository extends JpaRepository<OrdemDeServico,Integer> {
    boolean existsByVeiculo(Veiculo veiculo);
}
