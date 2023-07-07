package com.example.api.controller;

import com.example.api.exception.ResourceNotFoundException;
import com.example.api.model.Endereco;
import com.example.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoController(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping
    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @GetMapping("/{id}")
    public Endereco getEnderecoById(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com o ID: " + id));
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco enderecoDetails) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com o ID: " + id));

        endereco.setCep(enderecoDetails.getCep());
        endereco.setLogradouro(enderecoDetails.getLogradouro());
        endereco.setNumero(enderecoDetails.getNumero()); // Adicione esta linha para atualizar o número da residência
        endereco.setBairro(enderecoDetails.getBairro());
        endereco.setCidade(enderecoDetails.getCidade());
        endereco.setUf(enderecoDetails.getUf());

        return enderecoRepository.save(endereco);
    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Long id) {
        enderecoRepository.deleteById(id);
    }
}
