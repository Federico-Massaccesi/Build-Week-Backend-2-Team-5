package it.epicode.ENello.Management.dto;

import it.epicode.ENello.Management.entities.StatoFattura;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class FattureDto {

    private LocalDate data;

    private Double importo;

    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

    private Integer idCliente;
}
