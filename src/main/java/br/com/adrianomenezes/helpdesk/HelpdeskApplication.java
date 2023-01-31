package br.com.adrianomenezes.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adrianomenezes.helpdesk.domain.Chamado;
import br.com.adrianomenezes.helpdesk.domain.Cliente;
import br.com.adrianomenezes.helpdesk.domain.Tecnico;
import br.com.adrianomenezes.helpdesk.domain.enums.Perfil;
import br.com.adrianomenezes.helpdesk.domain.enums.Prioridade;
import br.com.adrianomenezes.helpdesk.domain.enums.Status;
import br.com.adrianomenezes.helpdesk.repositories.ChamadoRepository;
import br.com.adrianomenezes.helpdesk.repositories.ClienteRepository;
import br.com.adrianomenezes.helpdesk.repositories.TecnicoRepository;


@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "tecnico 1", "65416818808", "tec1@tecnico.com", "123456" );
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "cliente 1", "98122059007", "cli1@cliente.com", "123456" );
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 01", "observacao do primeiro chamado",
		tec1, cli1);

		Chamado c2 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 02", "observacao do segundo chamado",
		tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1,c2));


	}

}
