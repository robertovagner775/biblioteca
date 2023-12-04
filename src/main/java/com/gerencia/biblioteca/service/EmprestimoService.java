package com.gerencia.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gerencia.biblioteca.model.Cliente;
import com.gerencia.biblioteca.model.Emprestimo;
import com.gerencia.biblioteca.model.Livro;
import com.gerencia.biblioteca.model.dto.DevolucaoDto;
import com.gerencia.biblioteca.model.dto.EmprestimoDto;
import com.gerencia.biblioteca.model.response.EstoqueResponse;
import com.gerencia.biblioteca.repository.ClienteRepository;
import com.gerencia.biblioteca.repository.EmprestimoRepository;
import com.gerencia.biblioteca.repository.LivroRepository;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    public ResponseEntity realizarEmprestimo(EmprestimoDto emprestimoDto) {
        Cliente cliente = clienteRepository.findViewByCpf(emprestimoDto.cpf());
        Livro livro = livroRepository.findById(emprestimoDto.id_livro()).get();
        if(livro.getStatus() == livro.getStatus().DISPONIVEL) {
            livro.setStatus(livro.getStatus().EMPRESTADO);
            livroRepository.save(livro);
            Emprestimo emp = new Emprestimo(livro, cliente);
            emprestimoRepository.save(emp);
            return ResponseEntity.ok().body(new EstoqueResponse("emprestimo realizado", "emprestimo realizado para " + cliente.getNome(), "Livro: " + livro.getTitulo()));
        }
        return ResponseEntity.badRequest().body(new EstoqueResponse("emprestimo não realizado", "o livro não está disponivel", "Livro: não disponivel"));


    }

    public ResponseEntity devolverEmprestimo(DevolucaoDto devolucaoDto) {
      Emprestimo emprestimo =  emprestimoRepository.findByEmprestimoLivro(devolucaoDto.cpf(), devolucaoDto.id_livro());
      Livro livro = livroRepository.findById(devolucaoDto.id_livro()).get();
      livro.setStatus(livro.getStatus().DISPONIVEL);
      livroRepository.save(livro);      
      emprestimoRepository.delete(emprestimo);
      return ResponseEntity.ok().body(new EstoqueResponse("devolução de livro", "devolução do livro emprestado para: " + emprestimo.getCliente().getNome(), "Livro: " + livro.getTitulo()));
    }

    public ResponseEntity visualizarEmprestimo() {
        return ResponseEntity.ok().body(emprestimoRepository.findAll());
    }

    public ResponseEntity visualizarEmprestimoCpf(String cpf) {
        return ResponseEntity.ok().body(emprestimoRepository.findByCliente_cpf(cpf));
    }

}
