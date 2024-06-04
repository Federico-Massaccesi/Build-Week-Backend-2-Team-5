package it.epicode.ENello.Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Indirizzo extends BaseEntity{

    private String via;
    private String civico;
    private String localita;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cliente_sede_legale_id")
    private Cliente clienteSedeLegale;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cliente_sede_operativa_id")
    private Cliente clienteSedeOperativa;
}
