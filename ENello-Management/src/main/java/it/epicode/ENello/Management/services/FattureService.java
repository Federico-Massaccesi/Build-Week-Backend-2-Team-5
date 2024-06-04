package it.epicode.ENello.Management.services;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.repositories.FattureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        //fatture.getCliente().getId()

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
}
