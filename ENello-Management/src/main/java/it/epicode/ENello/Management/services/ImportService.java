package it.epicode.ENello.Management.services;
import it.epicode.ENello.Management.entities.Comune;
import it.epicode.ENello.Management.entities.Provincia;
import it.epicode.ENello.Management.repositories.ComuneRepository;
import it.epicode.ENello.Management.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class ImportService {
    @Autowired
    public ProvinciaRepository provinciaRepository;

    @Autowired
    public ComuneRepository comuneRepository;

    public void importaProvince(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            br.readLine();
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] fields = line.split(";");
                if (fields.length == 3) {
                    Provincia provincia = Provincia.builder()
                            .withSigla(fields[0].trim())
                            .withNome(fields[1].trim())
                            .withRegione(fields[2].trim())
                            .build();
                    provinciaRepository.save(provincia);
                } else {
                    System.err.println("Riga " + lineNumber + " non valida: " + line);
                }
            }
        }
    }


    public void importaComuni(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            br.readLine();
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] fields = line.split(";");
                Optional<Provincia> provincia = provinciaRepository.findByNome(fields[3]);
                if (provincia.isPresent()) {
                    Comune comune = Comune.builder()
                            .withNome(fields[2])
                            .withProvincia(provincia.get())
                            .withRegione(provincia.get().getRegione())
                            .build();
                    comuneRepository.save(comune);
                } else {
                    System.err.println("Riga " + lineNumber + " non valida: " + line);
                }
            }
        }
    }
}