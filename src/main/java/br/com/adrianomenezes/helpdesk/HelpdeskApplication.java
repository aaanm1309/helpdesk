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



	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}

}
