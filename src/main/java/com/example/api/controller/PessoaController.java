package com.example.api.controller;

import com.example.api.exception.ResourceNotFoundException;
import com.example.api.model.Pessoa;
import com.example.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @GetMapping("/{id}")
    public Pessoa getPessoaById(@PathVariable Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
    }

    @PutMapping("/{id}")
    public Pessoa updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetails) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));

        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setCpf(pessoaDetails.getCpf());
        pessoa.setEmail(pessoaDetails.getEmail());
        pessoa.setTelefone(pessoaDetails.getTelefone());
        pessoa.setEndereco(pessoaDetails.getEndereco());

        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }
}
