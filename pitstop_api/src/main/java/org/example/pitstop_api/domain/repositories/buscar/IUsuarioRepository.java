package org.example.pitstop_api.domain.repositories.buscar;

import org.example.pitstop_api.domain.entities.Oficina;
import org.example.pitstop_api.domain.entities.buscar.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
}
