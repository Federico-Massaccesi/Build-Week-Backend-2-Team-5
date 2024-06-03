package it.epicode.ENello.Management.mappers;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.validators.ClienteValidator;
import org.springframework.stereotype.Service;

@Service
public class MapToCliente {

    public Cliente convertToEntity(ClienteValidator clienteValidator) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(clienteValidator.ragioneSociale());
        cliente.setPartitaIva(clienteValidator.partitaIva());
        cliente.setEmail(clienteValidator.email());
        cliente.setDataInserimento(clienteValidator.dataInserimento());
        cliente.setDataUltimoContatto(clienteValidator.dataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteValidator.fatturatoAnnuale());
        cliente.setPec(clienteValidator.pec());
        cliente.setTelefono(clienteValidator.telefono());
        cliente.setEmailContatto(clienteValidator.emailContatto());
        cliente.setNomeContatto(clienteValidator.nomeContatto());
        cliente.setCognomeContatto(clienteValidator.cognomeContatto());
        cliente.setTelefonoContatto(clienteValidator.telefonoContatto());
        cliente.setLogoAziendale(clienteValidator.logoAziendale());
        cliente.setSedeLegale(clienteValidator.sedeLegale());
        cliente.setSedeOperativa(clienteValidator.sedeOperativa());
        cliente.setFatture(clienteValidator.fatture());
        return cliente;
    }
}
