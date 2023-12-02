package com.gerencia.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gerencia.biblioteca.model.Editora;
import com.gerencia.biblioteca.model.Escritor;
import com.gerencia.biblioteca.repository.EditoraRepository;
import com.gerencia.biblioteca.repository.EscritorRepository;

@Service
public class EditoraEscritorService {
    
    @Autowired
    private EscritorRepository escritorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    public ResponseEntity<?> addEscritor(@RequestBody Escritor escritor) {
        Escritor esc = escritorRepository.save(escritor);
        return ResponseEntity.ok().body(esc);
    } 
    public ResponseEntity<?> removeEscritor(@RequestBody Long idEscritor) {
        escritorRepository.deleteById(idEscritor);
        return ResponseEntity.ok().build(); 
    }
    public ResponseEntity<?> addEditora(@RequestBody Editora editora) {
        if(editoraRepository.save(editora) != null) return ResponseEntity.status(201).build(); 
        else return ResponseEntity.badRequest().build();       
    }



}
