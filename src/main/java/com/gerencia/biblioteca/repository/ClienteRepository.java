package com.gerencia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerencia.biblioteca.model.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  
    Cliente findViewByCpf(String cpf);
}
