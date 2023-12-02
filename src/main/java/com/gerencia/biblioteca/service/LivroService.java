package com.gerencia.biblioteca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gerencia.biblioteca.model.Editora;
import com.gerencia.biblioteca.model.Escritor;
import com.gerencia.biblioteca.model.Livro;
import com.gerencia.biblioteca.model.dto.LivroDto;
import com.gerencia.biblioteca.model.StatusLivro;
import com.gerencia.biblioteca.model.response.EstoqueResponse;
import com.gerencia.biblioteca.repository.EditoraRepository;
import com.gerencia.biblioteca.repository.EscritorRepository;
import com.gerencia.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EscritorRepository escritorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    public ResponseEntity<EstoqueResponse> addLivro(@RequestBody LivroDto livroDto) {
        Escritor escritor = escritorRepository.findById(livroDto.escritor_id()).get();
        Editora editora = editoraRepository.findById(livroDto.editora_id()).get();
        Livro livro = new Livro( livroDto.titulo(), livroDto.sinopse(), escritor, editora);
        if(livroRepository.save(livro) != null ) return ResponseEntity.status(201).body(new EstoqueResponse("adicionando livro estoque", "sucesso ao adicionar", "livro: "+ livroDto.titulo()));
        else return ResponseEntity.badRequest().body(new EstoqueResponse("erro ao adicionar livro no estoque", "error ao realizar tafefa", "livro"));
    }

    public ResponseEntity<?> viewLivro() {
        return ResponseEntity.ok().body(livroRepository.findAll());
    }

    public ResponseEntity<?> removeLivro(Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(new EstoqueResponse("erro produto não existe", "verifique o id inserido", "id livro"));
        }
    }

    public ResponseEntity<?> editLivro(LivroDto livroDto) {
        Livro livro = livroRepository.findById(livroDto.id()).get();
        livro.setEditora(editoraRepository.findById(livroDto.editora_id()).get());
        livro.setData_adicao(new Date());
        livro.setEscritor(escritorRepository.findById(livroDto.escritor_id()).get());
        livro.setSinopse(livroDto.sinopse());
        livro.setTitulo(livroDto.titulo());
        livroRepository.save(livro);
        return ResponseEntity.ok().build();
    }

    


}
