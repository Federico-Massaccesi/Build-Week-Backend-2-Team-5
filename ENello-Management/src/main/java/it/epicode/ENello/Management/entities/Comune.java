package it.epicode.ENello.Management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Comune extends BaseEntity{

    private String nome;
    private String Regione;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}
