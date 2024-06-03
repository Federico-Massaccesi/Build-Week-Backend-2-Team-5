package it.epicode.ENello.Management.services;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    public Page<Cliente> getAllClienti(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente getClienteById(Long id){
        if(clienteRepository.findById(id).isPresent()) {
            return clienteRepository.findById(id).get();
        }else{
            throw new RuntimeException();//SOSTITUIRE CON ECCEZIONE
        }
    }

    public Cliente saveCliente(Cliente cliente){
        if (cliente.getSedeLegale() != null) {
            cliente.getSedeLegale().setClienteSedeLegale(cliente);
        }
        if (cliente.getSedeOperativa() != null) {
            cliente.getSedeOperativa().setClienteSedeOperativa(cliente);
        }
        return clienteRepository.save(cliente);
    }

    public Cliente deleteCliente(Long id){
        if(clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            return clienteRepository.findById(id).get();
        }else{
            throw new RuntimeException();//SOSTITUIRE CON ECCEZIONE
        }
    }

}
