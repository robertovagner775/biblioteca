package com.gerencia.biblioteca.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emprestimo")
@Entity(name = "emprestimo")
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Livro.class)
    @JoinColumn(nullable = false, name = "id_livro")
    private Livro livro;

    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(nullable = false, name = "id_cliente")
    private Cliente cliente;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    

    public Emprestimo(Livro livro, Cliente cliente) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = LocalDate.now().plusDays(15);
    }
    
}
