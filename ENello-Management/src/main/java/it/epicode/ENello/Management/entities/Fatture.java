package it.epicode.ENello.Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Fatture  extends BaseEntity{

    private LocalDate data;

    private Double importo;

    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;
}
