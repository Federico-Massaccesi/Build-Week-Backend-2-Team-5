package it.epicode.ENello.Management.services;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.repositories.ClienteRepository;
import it.epicode.ENello.Management.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public ComuneRepository comuneRepository;

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

        var comuneSLegale = comuneRepository.findByNome(cliente.getSedeLegale().getComune().getNome());

        comuneSLegale.ifPresent(comune -> cliente.getSedeLegale().setComune(comune));

        var comuneSOperativa = comuneRepository.findByNome(cliente.getSedeOperativa().getComune().getNome());

        comuneSOperativa.ifPresent(comune -> cliente.getSedeOperativa().setComune(comune));

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
    public Page<Cliente> getClientiByNome(String nome, Pageable pageable) {
        return clienteRepository.findByNomeContaining(nome, pageable);
    }

    public Page<Cliente> getClientiByFatturatoAnnuale(Double fatturatoAnnuale, Pageable pageable) {
        return clienteRepository.findByFatturatoAnnualeGreaterThanEqual(fatturatoAnnuale, pageable);
    }

    public Page<Cliente> getClientiByDataInserimento(LocalDate dataInserimento, Pageable pageable) {
        return clienteRepository.findByDataInserimentoAfter(dataInserimento, pageable);
    }

    public Page<Cliente> getClientiByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable) {
        return clienteRepository.findByDataUltimoContattoAfter(dataUltimoContatto, pageable);
    }

    public Page<Cliente> getClientiByProvinciaSedeLegale(String provincia, Pageable pageable) {
        return clienteRepository.findByProvinciaSedeLegale(provincia, pageable);
    }

    public Page<Cliente> getClientiByFilters(Double fatturatoAnnuale, LocalDate dataInserimento, LocalDate dataUltimoContatto, String nome, Pageable pageable) {
        return clienteRepository.findByFilters(fatturatoAnnuale, dataInserimento, dataUltimoContatto, nome, pageable);
    }

}
