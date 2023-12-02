package com.gerencia.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerencia.biblioteca.model.Cliente;
import com.gerencia.biblioteca.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
        return clienteService.addCliente(cliente);
    }

    @PutMapping
    public ResponseEntity<?> editCliente(@RequestBody Cliente cliente) {
        return clienteService.editCliente(cliente);
    }
}
