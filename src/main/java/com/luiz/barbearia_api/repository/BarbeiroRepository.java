package com.luiz.barbearia_api.repository;

import com.luiz.barbearia_api.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository
        extends JpaRepository<Barbeiro, Long> {
}