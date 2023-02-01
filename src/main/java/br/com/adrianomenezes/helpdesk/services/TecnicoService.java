package br.com.adrianomenezes.helpdesk.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adrianomenezes.helpdesk.domain.Tecnico;
import br.com.adrianomenezes.helpdesk.repositories.TecnicoRepository;
import br.com.adrianomenezes.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tec =  tecnicoRepository.findById(id);
        return tec.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com id: " + id));
    
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    
}
