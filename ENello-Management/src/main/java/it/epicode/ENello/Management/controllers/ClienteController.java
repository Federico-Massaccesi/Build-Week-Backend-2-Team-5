package it.epicode.ENello.Management.controllers;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.services.ClienteService;
import it.epicode.ENello.Management.validators.ClienteValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

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
    public ClienteValidator createCliente(@Valid @RequestBody ClienteValidator cliente) {
        // Convert validator to entity as needed
        return clienteService.saveCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}