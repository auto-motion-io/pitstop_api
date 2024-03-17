package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque,Integer> {
}
