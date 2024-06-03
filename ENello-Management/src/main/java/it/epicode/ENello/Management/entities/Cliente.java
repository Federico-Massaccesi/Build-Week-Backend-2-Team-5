package it.epicode.ENello.Management.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")

public class Cliente extends BaseEntity{

    private String ragioneSociale;
    private String partitaIva;
    private String email;

    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Integer fatturatoAnnuale;

    private String pec;
    private Integer telefono;

    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;

    private Integer telefonoContatto;
    private String logoAziendale;

    @OneToOne(mappedBy = "clienteSedeLegale", cascade = CascadeType.ALL)
    private Indirizzo sedeLegale;

    @OneToOne(mappedBy = "clienteSedeOperativa", cascade = CascadeType.ALL)
    private Indirizzo sedeOperativa;

    @OneToMany(mappedBy ="cliente", cascade = CascadeType.ALL)
    private List<Fatture> fatture;
}
