package br.com.adrianomenezes.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adrianomenezes.helpdesk.domain.Tecnico;
import br.com.adrianomenezes.helpdesk.domain.dtos.TecnicoDTO;
import br.com.adrianomenezes.helpdesk.services.TecnicoService;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tec =  tecnicoService.findById(id);
        return ResponseEntity.ok(new TecnicoDTO(tec));
        

    } 

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> tecs =  tecnicoService.findAll();
        return ResponseEntity.ok(tecs.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList()));
        

    } 


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecDto){
        Tecnico tec =  tecnicoService.save(tecDto);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(tec.getId())
                    .toUri();
        return ResponseEntity.created( uri).build();

    } 

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecDto){
        Tecnico tec =  tecnicoService.update(id, tecDto);

        return ResponseEntity.ok( new TecnicoDTO(tec));
        

    } 

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        tecnicoService.delete(id);

        return ResponseEntity.noContent().build();
        

    } 
    
    
}
 