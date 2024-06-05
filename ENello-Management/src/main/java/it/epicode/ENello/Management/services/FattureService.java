package it.epicode.ENello.Management.services;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.entities.StatoFattura;
import it.epicode.ENello.Management.repositories.FattureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FattureService {

    @Autowired
    FattureRepository fattureRepository;

    public Page<Fatture> getAllFatture(Pageable pageable){
        return fattureRepository.findAll(pageable);
    }

    public Fatture getFattureById(Long id){
        if(fattureRepository.findById(id).isPresent()) {
            return fattureRepository.findById(id).get();
        }else{
            throw new RuntimeException();//SOSTITUIRE CON ECCEZIONE
        }
    }

    public Fatture saveFatture(Fatture fatture){

        return fattureRepository.save(fatture);
    }

    public Fatture deleteFatture(Long id){
        if(fattureRepository.findById(id).isPresent()) {
            fattureRepository.deleteById(id);
            return fattureRepository.findById(id).get();
        }else{
            throw new RuntimeException();//SOSTITUIRE CON ECCEZIONE
        }
    }
    public Page<Fatture> getFattureByCliente(Long clienteId, Pageable pageable) {
        return fattureRepository.findByCliente(clienteId, pageable);
    }

    public Page<Fatture> getFattureByStato(StatoFattura statoFattura, Pageable pageable) {
        return fattureRepository.findByStatoFattura(statoFattura, pageable);
    }

    public Page<Fatture> getFattureByData(LocalDate data, Pageable pageable) {
        return fattureRepository.findByData(data, pageable);
    }

    public Page<Fatture> getFattureByClienteAndDateRange(Long clienteId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return fattureRepository.findByClienteAndDateRange(clienteId, startDate, endDate, pageable);
    }
}
