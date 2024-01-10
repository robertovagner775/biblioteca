package com.gerencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.gerencia.biblioteca.model.Emprestimo;
import com.gerencia.biblioteca.model.dto.DevolucaoDto;
import com.gerencia.biblioteca.model.dto.EmprestimoDto;
import com.gerencia.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    

    @Autowired
    private EmprestimoService emprestimoService;


    @PostMapping
    public ResponseEntity<?> realizarEmprestimo(@RequestBody EmprestimoDto emprestimoDto) {
        return emprestimoService.realizarEmprestimo(emprestimoDto);
    }

    @DeleteMapping("/devolucao")
    public ResponseEntity<?> realizarDevolucao(@RequestBody DevolucaoDto devolucaoDto) {
        return emprestimoService.devolverEmprestimo(devolucaoDto);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> visualizarEmprestimos() {
        return emprestimoService.visualizarEmprestimo();
    }

    @GetMapping(value = "/cpf")
    public ResponseEntity<?> visualizarEmprestimoCpf(@RequestParam("cpf") String cpf) {
        return emprestimoService.visualizarEmprestimoCpf(cpf);
    }
    
}
