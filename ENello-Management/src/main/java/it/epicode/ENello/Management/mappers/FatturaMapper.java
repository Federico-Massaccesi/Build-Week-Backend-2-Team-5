package it.epicode.ENello.Management.mappers;

import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.repositories.ClienteRepository;
import it.epicode.ENello.Management.validators.FatturaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FatturaMapper {

    @Autowired
    ClienteRepository clienteRepo;

    public Fatture mapToEntity(FatturaValidator validator) {

        var foundedClient = clienteRepo.findById(validator.idCliente());

        if (foundedClient.isPresent()) {
            return Fatture.builder()
                    .withData(validator.data())
                    .withCliente(foundedClient.get())
                    .withImporto(validator.importo())
                    .withStatoFattura(validator.statoFattura())
                    .build();
        }else{
            throw new RuntimeException(); //SOSTITUIRE CON ECCEZIONE
        }


    }
}
