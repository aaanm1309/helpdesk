package br.com.adrianomenezes.helpdesk.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adrianomenezes.helpdesk.domain.Pessoa;
import br.com.adrianomenezes.helpdesk.domain.Tecnico;
import br.com.adrianomenezes.helpdesk.domain.dtos.TecnicoDTO;
import br.com.adrianomenezes.helpdesk.repositories.PessoaRepository;
import br.com.adrianomenezes.helpdesk.repositories.TecnicoRepository;
import br.com.adrianomenezes.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.adrianomenezes.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public Tecnico findById(Integer id) {
        Optional<Tecnico> tec =  tecnicoRepository.findById(id);
        return tec.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com id: " + id));
    
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico save(TecnicoDTO tecDto) {
        tecDto.setId(null);
        validaPorCpfeEmail(tecDto);
        return tecnicoRepository.save(new Tecnico(tecDto));
    }

    private void validaPorCpfeEmail(TecnicoDTO tecDto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecDto.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != tecDto.getId()){
            throw new DataIntegrityViolationException("Cpf já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(tecDto.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != tecDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }

    }

    public Tecnico update(Integer id, @Valid TecnicoDTO tecDto) {
        tecDto.setId(id);
        findById(id);
        validaPorCpfeEmail(tecDto);
        return tecnicoRepository.save(new Tecnico(tecDto));
    }

    
}
