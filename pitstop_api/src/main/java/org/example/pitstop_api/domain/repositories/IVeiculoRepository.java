package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.pitstop.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVeiculoRepository extends JpaRepository<Veiculo,Integer> {
}
