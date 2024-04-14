package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.motion.motion_api.domain.entities.pitstop.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServicoRepository extends JpaRepository<Servico,Integer> {
    List<Servico> findByNomeIn(List<String> nomes);
}
