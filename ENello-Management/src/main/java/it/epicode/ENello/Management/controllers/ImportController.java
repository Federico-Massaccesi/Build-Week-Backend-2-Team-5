package it.epicode.ENello.Management.controllers;

import it.epicode.ENello.Management.services.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/import")
public class ImportController {


    @Autowired
    ImportService svc;

    @PostMapping("/province")
    public String importaProvince(@RequestParam("file") MultipartFile file) {
        log.debug("Received file {}", file.getOriginalFilename());
        try {
            svc.importaProvince(file);
            return "Importazione province completata.";
        } catch (IOException e) {
            return "Errore durante l'importazione delle province: " + e.getMessage();
        }
    }

    @PostMapping("/comuni")
    public String importaComuni(@RequestParam("file") MultipartFile file) {
        try {
            svc.importaComuni(file);
            return "Importazione comuni completata.";
        } catch (IOException e) {
            return "Errore durante l'importazione dei comuni: " + e.getMessage();
        }
    }
}
