package it.epicode.ENello.Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Provincia extends BaseEntity{

    private String nome;
    private String sigla;
    private String regione;
@JsonIgnore
    @OneToMany(mappedBy ="provincia", cascade = CascadeType.ALL)
    private List<Comune> comuni;
}
