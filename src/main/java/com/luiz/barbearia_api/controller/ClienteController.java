package com.luiz.barbearia_api.controller;

import com.luiz.barbearia_api.model.Cliente;
import com.luiz.barbearia_api.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://barbearia-83s8.vercel.app"
})
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    // LISTAR TODOS
    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // CRIAR CLIENTE (CADASTRO)
    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        System.out.println("🔥 SALVANDO CLIENTE: " + cliente.getEmail());
        return repository.save(cliente);
    }

    // ATUALIZAR CLIENTE
    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {

        Cliente cliente = repository.findById(id).orElse(null);

        if (cliente == null) {
            return null;
        }

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setSenha(clienteAtualizado.getSenha());
        cliente.setCargo(clienteAtualizado.getCargo());

        return repository.save(cliente);
    }

    // DELETA
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // LOGIN
    @PostMapping("/login")
    public Cliente login(@RequestBody Cliente login) {

        Cliente cliente = repository.findByEmail(login.getEmail()).orElse(null);

        if (cliente == null) {
            return null;
        }

        if (!cliente.getSenha().equals(login.getSenha())) {
            return null;
        }

        return cliente;
    }
}