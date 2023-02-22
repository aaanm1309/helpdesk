package br.com.adrianomenezes.helpdesk.domain;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.adrianomenezes.helpdesk.domain.dtos.TecnicoDTO;
import br.com.adrianomenezes.helpdesk.domain.enums.Perfil;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
        addPerfil(Perfil.CLIENTE);
    }


    public Tecnico( String nome, String cpf, String email, String senha) {
        super( nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDTO tecDto){
        super();
        this.id = tecDto.getId();
        this.nome = tecDto.getNome();
        this.cpf = tecDto.getCpf();
        this.email = tecDto.getEmail();
        this.senha = tecDto.getSenha();

        this.perfis =  tecDto.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());

        this.dataCriacao = tecDto.getDataCriacao();
        addPerfil(Perfil.TECNICO);
        addPerfil(Perfil.CLIENTE);
    }


    

}
