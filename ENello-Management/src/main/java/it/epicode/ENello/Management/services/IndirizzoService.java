package it.epicode.ENello.Management.services;

import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.entities.Indirizzo;
import it.epicode.ENello.Management.repositories.ComuneRepository;
import it.epicode.ENello.Management.repositories.FattureRepository;
import it.epicode.ENello.Management.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {
    @Autowired
    IndirizzoRepository indirizzoRepository;

    @Autowired
    ComuneRepository comuniRepo;

    public Page<Indirizzo> getAllIndirizzi(Pageable pageable){



        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo getIndirizziById(Long id){
        if(indirizzoRepository.findById(id).isPresent()) {
            return indirizzoRepository.findById(id).get();
        }else{
            throw new RuntimeException();//SOSTITUIRE CON ECCEZIONE
        }
    }

    public Indirizzo saveIndirizzo(Indirizzo indirizzo){


        return indirizzoRepository.save(indirizzo);
    }

    public Indirizzo deleteIndirizzo(Long id){
        if(indirizzoRepository.findById(id).isPresent()) {
            indirizzoRepository.deleteById(id);
            return indirizzoRepository.findById(id).get();
        }else{
            throw new RuntimeException();//SOSTITUIRE CON ECCEZIONE
        }
    }
}