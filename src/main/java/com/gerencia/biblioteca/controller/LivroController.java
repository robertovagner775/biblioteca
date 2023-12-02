package com.gerencia.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerencia.biblioteca.model.Editora;
import com.gerencia.biblioteca.model.Escritor;
import com.gerencia.biblioteca.model.Livro;
import com.gerencia.biblioteca.model.dto.LivroDto;
import com.gerencia.biblioteca.service.EditoraEscritorService;
import com.gerencia.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {
    
    @Autowired
    private EditoraEscritorService editoraEscritorService;

    @Autowired
    private LivroService livroService;


    @GetMapping
    public ResponseEntity<?> viewLivro() {
        return livroService.viewLivro();
    }

    @PostMapping("/escritor")
    public ResponseEntity adicionarEscritorEstoque(@RequestBody Escritor escritor) {
        return editoraEscritorService.addEscritor(escritor);
    }

    @DeleteMapping(value = "/escritor/id")
    public ResponseEntity removeEscritor(@RequestParam("id") Long id) {
        return editoraEscritorService.removeEscritor(id);    
    }

    @PostMapping("/editora")
    public ResponseEntity adicionarEditoraEstoque(@RequestBody Editora editora) {
        return editoraEscritorService.addEditora(editora);
    }

    @PostMapping
    public ResponseEntity<?> adicionarNovoLivro(@RequestBody LivroDto livroDto) {
        return livroService.addLivro(livroDto);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity removeLivro(@RequestParam("id") Long id) {
        return livroService.removeLivro(id);
    }

    @PutMapping
    public ResponseEntity editLivro(@RequestBody LivroDto livroDto) {
        return livroService.editLivro(livroDto);
    }



    


}
