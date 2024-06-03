package it.epicode.ENello.Management.controllers;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.mappers.MapToCliente;
import it.epicode.ENello.Management.services.ClienteService;
import it.epicode.ENello.Management.validators.ClienteValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    MapToCliente mapper;

    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllClienti(Pageable pageable) {
        return new ResponseEntity<>(clienteService.getAllClienti(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteValidator cliente, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException(validation.getAllErrors().toString());
        }

    var newClient = mapper.convertToEntity(cliente);
         clienteService.saveCliente(newClient);

         return new ResponseEntity<>(newClient,HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
        var client = clienteService.deleteCliente(id);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }
}