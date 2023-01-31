package br.com.adrianomenezes.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianomenezes.helpdesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
    
}
