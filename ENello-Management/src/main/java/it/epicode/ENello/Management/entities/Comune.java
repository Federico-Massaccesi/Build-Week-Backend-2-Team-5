package it.epicode.ENello.Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Comune extends BaseEntity{

    private String nome;
    private String regione;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @JsonIgnore
    @OneToMany(mappedBy = "comune", cascade = CascadeType.ALL)
    private List<Indirizzo> indirizzi;
}
