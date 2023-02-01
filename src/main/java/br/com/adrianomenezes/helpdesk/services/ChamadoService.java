package br.com.adrianomenezes.helpdesk.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.adrianomenezes.helpdesk.domain.Chamado;
import br.com.adrianomenezes.helpdesk.domain.Cliente;
import br.com.adrianomenezes.helpdesk.domain.Tecnico;
import br.com.adrianomenezes.helpdesk.domain.dtos.ChamadoDTO;
import br.com.adrianomenezes.helpdesk.repositories.ChamadoRepository;

import br.com.adrianomenezes.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;


@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado =  chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado com id: " + id));
    
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado save(ChamadoDTO chamadoDto) {
        chamadoDto.setId(null);
   
        return chamadoRepository.save(newChamado(chamadoDto));
    }

    private Chamado newChamado(ChamadoDTO chamadoDto){
        Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDto.getCliente());

        Chamado chamado = new Chamado(chamadoDto, tecnico, cliente);
        // if (chamadoDto.getId() != null ) {
        //     chamado.setId(chamadoDto.getId());
        // }
        return chamado;

           
    }

    public Chamado update(Integer id, @Valid ChamadoDTO chamadoDto) {
        chamadoDto.setId(id);
        findById(id);

        return chamadoRepository.save(newChamado(chamadoDto));
    }

    // public void delete(Integer id) {
    //     Chamado tec = findById(id);
    //     if (tec.getChamados().size() > 0) {
    //         throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não podem ser deletados");
    //     }
    //     clienteRepository.deleteById(id);

    // }

    
}
