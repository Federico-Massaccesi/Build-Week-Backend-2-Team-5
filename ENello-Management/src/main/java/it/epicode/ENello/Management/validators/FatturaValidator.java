package it.epicode.ENello.Management.validators;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.entities.StatoFattura;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FatturaValidator(
        @NotNull(message = "Data è obbligatoria")
        LocalDate data,

        @NotNull(message = "Importo è obbligatorio")
        Double importo,

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Stato fattura è obbligatorio")
        StatoFattura statoFattura,


        @NotNull(message = "Cliente è obbligatorio")
        Long idCliente) {
}
