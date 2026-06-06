package com.luiz.barbearia_api.repository;

import com.luiz.barbearia_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(
            String email
    );
}