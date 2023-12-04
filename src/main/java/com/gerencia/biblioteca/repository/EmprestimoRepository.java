package com.gerencia.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerencia.biblioteca.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT e FROM emprestimo e JOIN e.livro l JOIN e.cliente c WHERE c.cpf = :cpf AND l.id_livro = :id")
    Emprestimo findByEmprestimoLivro(String cpf, Long id);

    List<Emprestimo> findByCliente_cpf(String cpf);
}