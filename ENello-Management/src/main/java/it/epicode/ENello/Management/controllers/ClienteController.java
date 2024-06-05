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

import java.time.LocalDate;
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

    @GetMapping("/nome")
    public ResponseEntity<Page<Cliente>> getClientiByNome(@RequestParam String nome, Pageable pageable) {
        return new ResponseEntity<>(clienteService.getClientiByNome(nome, pageable), HttpStatus.OK);
    }

    @GetMapping("/fatturatoAnnuale")
    public ResponseEntity<Page<Cliente>> getClientiByFatturatoAnnuale(@RequestParam Double fatturatoAnnuale, Pageable pageable) {
        return new ResponseEntity<>(clienteService.getClientiByFatturatoAnnuale(fatturatoAnnuale, pageable), HttpStatus.OK);
    }

    @GetMapping("/dataInserimento")
    public ResponseEntity<Page<Cliente>> getClientiByDataInserimento(@RequestParam LocalDate dataInserimento, Pageable pageable) {
        return new ResponseEntity<>(clienteService.getClientiByDataInserimento(dataInserimento, pageable), HttpStatus.OK);
    }

    @GetMapping("/dataUltimoContatto")
    public ResponseEntity<Page<Cliente>> getClientiByDataUltimoContatto(@RequestParam LocalDate dataUltimoContatto, Pageable pageable) {
        return new ResponseEntity<>(clienteService.getClientiByDataUltimoContatto(dataUltimoContatto, pageable), HttpStatus.OK);
    }

    @GetMapping("/provincia")
    public ResponseEntity<Page<Cliente>> getClientiByProvinciaSedeLegale(@RequestParam String provincia, Pageable pageable) {
        return new ResponseEntity<>(clienteService.getClientiByProvinciaSedeLegale(provincia, pageable), HttpStatus.OK);
    }

    @GetMapping("/filters")
    public ResponseEntity<Page<Cliente>> getClientiByFilters(
            @RequestParam(required = false) Double fatturatoAnnuale,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return new ResponseEntity<>(clienteService.getClientiByFilters(fatturatoAnnuale, dataInserimento, dataUltimoContatto, nome, pageable), HttpStatus.OK);
    }
}