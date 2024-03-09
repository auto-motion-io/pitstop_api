package org.example.pitstop_api.domain.repositories.pitstop;

import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.pitstop.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;

public interface IGerenteRepository extends JpaRepository<Gerente,Integer> {
     Gerente findGerenteByEmail(String email);
     boolean existsByEmail(String email);

     boolean existsByOficina(Oficina oficina);
}
