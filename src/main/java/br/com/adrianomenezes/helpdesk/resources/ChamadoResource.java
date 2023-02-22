package br.com.adrianomenezes.helpdesk.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adrianomenezes.helpdesk.domain.Chamado;

import br.com.adrianomenezes.helpdesk.domain.dtos.ChamadoDTO;

import br.com.adrianomenezes.helpdesk.services.ChamadoService;
import javax.validation.Valid;


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


    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDto){
        Chamado chamado =  service.save(chamadoDto);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(chamado.getId())
                    .toUri();
        return ResponseEntity.created(uri).build();

    } 

    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDto){
        Chamado chamado =  service.update(id, chamadoDto);

        return ResponseEntity.ok( new ChamadoDTO(chamado));
    } 

}