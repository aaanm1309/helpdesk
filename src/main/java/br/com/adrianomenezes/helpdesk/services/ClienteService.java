package br.com.adrianomenezes.helpdesk.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adrianomenezes.helpdesk.domain.Pessoa;
import br.com.adrianomenezes.helpdesk.domain.Cliente;
import br.com.adrianomenezes.helpdesk.domain.dtos.ClienteDTO;
import br.com.adrianomenezes.helpdesk.repositories.PessoaRepository;
import br.com.adrianomenezes.helpdesk.repositories.ClienteRepository;
import br.com.adrianomenezes.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.adrianomenezes.helpdesk.services.exceptions.ObjectnotFoundException;
import javax.validation.Valid;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public Cliente findById(Integer id) {
        Optional<Cliente> tec =  clienteRepository.findById(id);
        return tec.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com id: " + id));
    
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(ClienteDTO cliDto) {
        cliDto.setId(null);
        cliDto.encryptaSenha();

        validaPorCpfeEmail(cliDto);
        return clienteRepository.save(new Cliente(cliDto));
    }

    private void validaPorCpfeEmail(ClienteDTO cliDto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cliDto.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != cliDto.getId()){
            throw new DataIntegrityViolationException("Cpf já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(cliDto.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != cliDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }

    }

    public Cliente update(Integer id, @Valid ClienteDTO cliDto) {
        cliDto.setId(id);
        findById(id);
        validaPorCpfeEmail(cliDto);
        return clienteRepository.save(new Cliente(cliDto));
    }

    public void delete(Integer id) {
        Cliente tec = findById(id);
        if (tec.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não podem ser deletados");
        }
        clienteRepository.deleteById(id);

    }

    
}
