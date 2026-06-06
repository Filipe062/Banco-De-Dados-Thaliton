package com.luiz.barbearia_api.controller;

import com.luiz.barbearia_api.model.Agendamento;
import com.luiz.barbearia_api.repository.AgendamentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "http://localhost:5173")
public class AgendamentoController {

    private final AgendamentoRepository repository;

    public AgendamentoController(
            AgendamentoRepository repository
    ) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> salvar(
            @RequestBody Agendamento agendamento
    ) {

        boolean existe =
                repository.existsByDataAndHorarioAndProfissional(
                        agendamento.getData(),
                        agendamento.getHorario(),
                        agendamento.getProfissional()
                );

        if (existe) {
            return ResponseEntity
                    .badRequest()
                    .body("Horário já ocupado");
        }

        return ResponseEntity.ok(
                repository.save(agendamento)
        );
    }

    @GetMapping
    public List<Agendamento> listar() {
        return repository.findAll();
    }

    @GetMapping("/ocupados")
    public List<Agendamento> horariosOcupados(
            @RequestParam String data,
            @RequestParam String profissional
    ) {
        return repository.findByDataAndProfissional(
                data,
                profissional
        );
    }
}