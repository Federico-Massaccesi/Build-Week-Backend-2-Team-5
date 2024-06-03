package it.epicode.ENello.Management.validators;

import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.entities.Indirizzo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;


public record ClienteValidator (
        @NotNull(message = "Ragione sociale è obbligatoria")
        String ragioneSociale,

        @NotNull(message = "Partita IVA è obbligatoria")
        String partitaIva,

        @NotNull(message = "Email è obbligatoria")
        String email,

        @NotNull(message = "Data di inserimento è obbligatoria")
        LocalDate dataInserimento,

        @NotNull(message = "Data dell'ultimo contatto è obbligatoria")
        LocalDate dataUltimoContatto,

        @NotNull(message = "Fatturato annuale è obbligatorio")
        Integer fatturatoAnnuale,

        @NotNull(message = "PEC è obbligatoria")
        @Email(message ="Formato email non corretto")
        String pec,

        @NotNull(message = "Telefono è obbligatorio")
        Integer telefono,

        @NotNull(message = "Email del contatto è obbligatoria")
        @Email(message ="Formato email non corretto")
        String emailContatto,

        @NotNull(message = "Nome del contatto è obbligatorio")
        String nomeContatto,

        @NotNull(message = "Cognome del contatto è obbligatorio")
        String cognomeContatto,

        @NotNull(message = "Telefono del contatto è obbligatorio")
        Integer telefonoContatto,

        @NotNull(message = "Logo aziendale è obbligatorio")
        String logoAziendale,

        @NotNull(message = "Sede legale è obbligatoria")
        Indirizzo sedeLegale,

        @NotNull(message = "Sede operativa è obbligatoria")
        Indirizzo sedeOperativa,

        List<Fatture> fatture) {

}
