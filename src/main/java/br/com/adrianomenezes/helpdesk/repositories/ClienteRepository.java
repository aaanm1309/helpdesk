package br.com.adrianomenezes.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianomenezes.helpdesk.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
