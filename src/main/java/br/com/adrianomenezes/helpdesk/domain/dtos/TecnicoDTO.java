package br.com.adrianomenezes.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.adrianomenezes.helpdesk.domain.Tecnico;
import br.com.adrianomenezes.helpdesk.domain.enums.Perfil;
import lombok.Getter;
import lombok.Setter;


@Getter@Setter
public class TecnicoDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    protected Integer id;
    protected String nome;
    

    protected String cpf;

    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    

    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(){
        super();
    }

    public TecnicoDTO(Tecnico tecnico){
        super();
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();

        this.perfis =  tecnico.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());

        this.dataCriacao = tecnico.getDataCriacao();
        
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }


    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    

}
