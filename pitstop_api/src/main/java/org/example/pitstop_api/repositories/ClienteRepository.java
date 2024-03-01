package org.example.pitstop_api.repositories;

import org.example.pitstop_api.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {


    @Query("select u from Cliente u where u.email = ?1")
    Cliente findByEmail(String emailAddress);
}
