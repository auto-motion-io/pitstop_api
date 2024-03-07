package org.example.pitstop_api.domain.repositories;

import org.example.pitstop_api.domain.entities.pitstop.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {


    @Query("select u from Cliente u where u.email = ?1")
    Cliente findByEmail(String emailAddress);
}
