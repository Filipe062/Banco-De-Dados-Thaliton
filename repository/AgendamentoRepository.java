package com.luiz.barbearia_api.repository;

import com.luiz.barbearia_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByDataAndProfissional(
            String data,
            String profissional
    );

    boolean existsByDataAndHorarioAndProfissional(
            String data,
            String horario,
            String profissional
    );
}