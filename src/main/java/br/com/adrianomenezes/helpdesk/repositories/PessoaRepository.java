package br.com.adrianomenezes.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianomenezes.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
    
}
