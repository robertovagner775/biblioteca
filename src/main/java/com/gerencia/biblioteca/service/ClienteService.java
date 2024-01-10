package com.gerencia.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gerencia.biblioteca.model.Cliente;
import com.gerencia.biblioteca.model.response.EstoqueResponse;
import com.gerencia.biblioteca.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity addCliente(@RequestBody Cliente cliente) {
        if(clienteRepository.save(cliente) != null) return ResponseEntity.ok().body(new EstoqueResponse("Adicionando Cliente", "cliente adicionado a biblioteca", "cadastro cliente"));
        else return ResponseEntity.badRequest().body(new EstoqueResponse("Error ao Adicionar Cliente", "Dados Incorretos", "erro ao cadastro cliente"));
    }

    public ResponseEntity editCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok().body(clienteRepository.save(cliente));
    }


    /* 
    private Boolean verificaPenalidade() {
        
    }

    */

    public ResponseEntity viewClientes() {
        return ResponseEntity.ok().body(clienteRepository.findAll());
    }
}
