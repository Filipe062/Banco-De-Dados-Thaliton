package com.luiz.barbearia_api.controller;

import com.luiz.barbearia_api.model.Barbeiro;
import com.luiz.barbearia_api.repository.BarbeiroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://barbearia-83s8.vercel.app"
})
public class BarbeiroController {

    private final BarbeiroRepository repository;

    public BarbeiroController(BarbeiroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Barbeiro> listar() {
        return repository.findAll();
    }
}