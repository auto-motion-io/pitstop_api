package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.pitstop.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque,Integer> {
}
