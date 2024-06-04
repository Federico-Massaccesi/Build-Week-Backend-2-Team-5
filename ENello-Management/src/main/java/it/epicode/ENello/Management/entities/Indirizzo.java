package it.epicode.ENello.Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private int cap;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_sede_legale_id")
    private Cliente clienteSedeLegale;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_sede_operativa_id")
    private Cliente clienteSedeOperativa;
}
