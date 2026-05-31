package com.luiz.barbearia_api.repository;

import com.luiz.barbearia_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByProfissionalAndData(String profissional, String data);

    boolean existsByProfissionalAndDataAndHorario(
            String profissional,
            String data,
            String horario
    );
}