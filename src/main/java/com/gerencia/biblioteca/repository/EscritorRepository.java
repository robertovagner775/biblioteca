package com.gerencia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerencia.biblioteca.model.Escritor;

@Repository
public interface EscritorRepository extends JpaRepository<Escritor, Long> {
    
}
