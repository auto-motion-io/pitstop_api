package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque,Integer> {
}
