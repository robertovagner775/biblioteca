package com.gerencia.biblioteca.model;

import java.util.Date;

import com.gerencia.biblioteca.model.StatusLivro;

import jakarta.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livro")
@Entity(name = "livro")
public class Livro {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long id_livro;

    private String titulo;

    private String sinopse;

    private Date data_adicao;

    @ManyToOne(targetEntity = Escritor.class)
    @JoinColumn(nullable = false, name = "escritor_id")
    private Escritor escritor;

    @ManyToOne(targetEntity = Editora.class)
    @JoinColumn(nullable = false, name = "editora_id")
    private Editora editora;


    private StatusLivro status;

 
    public Livro( String titulo, String sinopse, Escritor escritor, Editora editora) {
     
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.editora = editora;
        this.escritor = escritor;
        this.data_adicao = new Date();
        this.status = getStatus().DISPONIVEL;

    }



}
