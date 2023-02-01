package br.com.adrianomenezes.helpdesk.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.adrianomenezes.helpdesk.domain.Chamado;

import br.com.adrianomenezes.helpdesk.domain.dtos.ChamadoDTO;

import br.com.adrianomenezes.helpdesk.services.ChamadoService;


@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado =  service.findById(id);
        return ResponseEntity.ok(new ChamadoDTO(chamado));
        

    } 

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> chamado =  service.findAll();
        return ResponseEntity.ok(chamado.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList()));
        

    } 


    // @PostMapping
    // public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO cliDto){
    //     Cliente tec =  clienteService.save(cliDto);
    //     URI uri = ServletUriComponentsBuilder
    //                 .fromCurrentRequest()
    //                 .path("/{id}")
    //                 .buildAndExpand(tec.getId())
    //                 .toUri();
    //     return ResponseEntity.created( uri).build();

    // } 

    
    // @PutMapping(value = "/{id}")
    // public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO cliDto){
    //     Cliente tec =  clienteService.update(id, cliDto);

    //     return ResponseEntity.ok( new ClienteDTO(tec));
        

    // } 

    // @DeleteMapping(value = "/{id}")
    // public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
    //     clienteService.delete(id);

    //     return ResponseEntity.noContent().build();
        

    // } 
    
    
}
 