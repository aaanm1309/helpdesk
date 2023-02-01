package br.com.adrianomenezes.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adrianomenezes.helpdesk.domain.Cliente;
import br.com.adrianomenezes.helpdesk.domain.dtos.ClienteDTO;
import br.com.adrianomenezes.helpdesk.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente tec =  clienteService.findById(id);
        return ResponseEntity.ok(new ClienteDTO(tec));
        

    } 

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> tecs =  clienteService.findAll();
        return ResponseEntity.ok(tecs.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList()));
        

    } 


    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO cliDto){
        Cliente tec =  clienteService.save(cliDto);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(tec.getId())
                    .toUri();
        return ResponseEntity.created( uri).build();

    } 

    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO cliDto){
        Cliente tec =  clienteService.update(id, cliDto);

        return ResponseEntity.ok( new ClienteDTO(tec));
        

    } 

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
        

    } 
    
    
}
 