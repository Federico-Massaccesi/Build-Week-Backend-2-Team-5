package it.epicode.ENello.Management.mappers;

import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.validators.FatturaValidator;

public class FatturaMapper {
    public static Fatture mapToEntity(FatturaValidator validator) {
        return Fatture.builder()
                .withData(validator.data())
                .withImporto(validator.importo())
                .withStatoFattura(validator.statoFattura())
                .withCliente(validator.cliente())
                .build();
    }
}
