package br.com.adrianomenezes.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.adrianomenezes.helpdesk.domain.dtos.ClienteDTO;
import br.com.adrianomenezes.helpdesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente( String nome, String cpf, String email, String senha) {
        super( nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO cliDto){
        super();
        this.id = cliDto.getId();
        this.nome = cliDto.getNome();
        this.cpf = cliDto.getCpf();
        this.email = cliDto.getEmail();
        this.senha = cliDto.getSenha();

        this.perfis =  cliDto.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());

        this.dataCriacao = cliDto.getDataCriacao();
        addPerfil(Perfil.TECNICO);
        addPerfil(Perfil.CLIENTE);
    }
    
}
