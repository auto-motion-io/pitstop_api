package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVeiculoRepository extends JpaRepository<Veiculo,Integer> {
}
