package com.gerencia.biblioteca.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.support.LocalConnectionFactoryBean;
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
        if(verificaDataPenalidadeCliente(cliente)) {
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
         return ResponseEntity.badRequest().body(new EstoqueResponse("emprestimo não autorizado pois voce recebeu uma penalidade", "emprestimo recusado por penalidade", "Livro: emprestimo não realizado"));

    }

    private Boolean verificaDataPenalidadeCliente(Cliente cliente) {
        LocalDate penalideCliente = cliente.getPenalidade();
        if(LocalDate.now().isEqual(penalideCliente) || LocalDate.now().isAfter(penalideCliente) || cliente.getPenalidade() == null){
            return true;
        }
        return false;
    }

    private Long verificaDataValidaEmprestimo(LocalDate devolucaoEmprestimo) {
		Long diasAtrasados = ChronoUnit.DAYS.between(devolucaoEmprestimo, LocalDate.now());		
        return diasAtrasados;
    }

    // alterar modo de penalidade e trocar por datas ao inves de um numero inteiro
    // ou outra forma melhor 
    // adicionar manipulação da penalidade no cliente service
    private void aplicarPenalidadeCliente(Long diasAtrasados, Emprestimo emprestimo) {
        Long penalidade  =  diasAtrasados * 2;
        LocalDate dataPenalidade = LocalDate.now().plusDays(penalidade);
        Cliente cliente = emprestimo.getCliente();
        cliente.setPenalidade(dataPenalidade);
        clienteRepository.save(cliente);
    }

    public ResponseEntity devolverEmprestimo(DevolucaoDto devolucaoDto) {
      Emprestimo emprestimo =  emprestimoRepository.findByEmprestimoLivro(devolucaoDto.cpf(), devolucaoDto.id_livro());
      Long diasAtrasados = verificaDataValidaEmprestimo(emprestimo.getDataDevolucao());
      String mensagem = "entrega data prevista";
      if(diasAtrasados >= 1) {
        aplicarPenalidadeCliente(diasAtrasados, emprestimo);
        mensagem = "penalidade aplicada de "+  diasAtrasados + " dias";
      }
      Livro livro = livroRepository.findById(devolucaoDto.id_livro()).get();
      livro.setStatus(livro.getStatus().DISPONIVEL);
      livroRepository.save(livro);      
      emprestimoRepository.delete(emprestimo);
      return ResponseEntity.ok().body(new EstoqueResponse("devolução de livro", "devolução do livro emprestado para: " + emprestimo.getCliente().getNome() + " " + mensagem, "Livro: " + livro.getTitulo()));
    }

    public ResponseEntity visualizarEmprestimo() {
        return ResponseEntity.ok().body(emprestimoRepository.findAll());
    }

    public ResponseEntity visualizarEmprestimoCpf(String cpf) {
        return ResponseEntity.ok().body(emprestimoRepository.findByCliente_cpf(cpf));
    }

}
