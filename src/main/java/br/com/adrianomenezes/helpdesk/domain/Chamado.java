package br.com.adrianomenezes.helpdesk.domain;

import java.time.LocalDate;

import br.com.adrianomenezes.helpdesk.domain.enums.Prioridade;
import br.com.adrianomenezes.helpdesk.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Chamado {
    private Integer id;
    private LocalDate dataAbertura = LocalDate.now();
    private LocalDate dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    private Tecnico tecnico;
    private Cliente cliente;
    

    
}
