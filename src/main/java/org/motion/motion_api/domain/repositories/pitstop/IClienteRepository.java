package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.pitstop.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IClienteRepository extends JpaRepository<Cliente,Integer> {


    @Query("select u from Cliente u where u.email = ?1")
    Cliente findByEmail(String emailAddress);
}
