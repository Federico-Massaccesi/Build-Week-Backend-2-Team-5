package it.epicode.ENello.Management.services;
import it.epicode.ENello.Management.repositories.ComuneRepository;
import it.epicode.ENello.Management.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;

@Service
public class DataLoadService {
    @Autowired
    public ProvinciaRepository provinciaRepository;

    @Autowired
    public ComuneRepository comuneRepository;

}
