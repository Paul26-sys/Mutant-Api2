package com.example.mutantdetector.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DnaSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_sequence")
    private String dna; // Cambiar a String para almacenar como JSON

    private boolean isMutant;

    // Getters y Setters
    // Lombok generará automáticamente estos métodos si usas @Getter y @Setter
}





