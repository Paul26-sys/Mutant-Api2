package com.example.mutantdetector.presentation.rest;

import com.example.mutantdetector.services.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private DnaService dnaService;

    @PostMapping
    public ResponseEntity<?> isMutant(@RequestBody DnaRequest request) {
        boolean result = dnaService.isMutant(request.getDna());
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

class DnaRequest {
    private String[] dna;

    public String[] getDna() {
        return dna;  // Devuelve el valor real de "dna"
    }

    public void setDna(String[] dna) {
        this.dna = dna;  // Asigna el valor de "dna"
    }
}


