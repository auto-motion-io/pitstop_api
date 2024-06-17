package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.Oficina;
import org.motion.motion_api.domain.entities.pitstop.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque,Integer> {
    List<ProdutoEstoque> findByNome(String nome);
    List<ProdutoEstoque> findByNomeIn(List<String> nomes);
    List<ProdutoEstoque> findByOficina(Oficina oficina);
    List<ProdutoEstoque> findByValorVendaBetween(double precoMinimo, double precoMaximo);
}