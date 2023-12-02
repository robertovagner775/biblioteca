package com.gerencia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerencia.biblioteca.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    Livro findByTitulo(String titulo);
    
}
