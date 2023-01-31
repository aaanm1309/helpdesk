package br.com.adrianomenezes.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianomenezes.helpdesk.domain.Tecnico;


public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
    
}
