package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque,Integer> {
    List<ProdutoEstoque> findByNome(String nome);
    List<ProdutoEstoque> findByNomeIn(List<String> nomes);
    List<ProdutoEstoque> findByOficina(Oficina oficina);
}