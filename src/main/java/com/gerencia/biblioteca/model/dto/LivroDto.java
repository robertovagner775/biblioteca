package com.gerencia.biblioteca.model.dto;

import java.util.Date;

public record LivroDto(Long id,String titulo,String sinopse, Long escritor_id, Long editora_id) {


} 
