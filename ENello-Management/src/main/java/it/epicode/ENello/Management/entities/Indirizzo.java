package it.epicode.ENello.Management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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

    private String comune;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
