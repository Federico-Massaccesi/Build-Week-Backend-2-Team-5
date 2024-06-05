package it.epicode.ENello.Management.controllers;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.mappers.FatturaMapper;
import it.epicode.ENello.Management.mappers.MapToCliente;
import it.epicode.ENello.Management.services.FattureService;
import it.epicode.ENello.Management.validators.ClienteValidator;
import it.epicode.ENello.Management.validators.FatturaValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
    @Autowired
    private FattureService fattureService;


    @Autowired
    private FatturaMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Fatture>> getAllFatture(Pageable pageable) {
        return new ResponseEntity<>(fattureService.getAllFatture(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fatture> getFattureById(@PathVariable Long id) {
        Fatture fatture = fattureService.getFattureById(id);
        return new ResponseEntity<>(fatture, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fatture> createFattura(@Valid @RequestBody FatturaValidator fattura, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException(validation.getAllErrors().toString());
        }
        var newFattura = mapper.mapToEntity(fattura);
        fattureService.saveFatture(newFattura);
        return new ResponseEntity<>(newFattura,HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Fatture> deleteFattura(@PathVariable Long id) {
        var client = fattureService.deleteFatture(id);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }
}
