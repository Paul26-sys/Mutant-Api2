/*package com.example.mutantdetector.repositories;

import com.example.mutantdetector.domain.entities.DnaSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaSequenceRepository extends JpaRepository<DnaSequence, Long> {
    boolean existsByDna(String dna); // Cambiar a String
    DnaSequence findByDna(String dna); // Cambiar a String
}*/
package com.example.mutantdetector.repositories;

import com.example.mutantdetector.domain.entities.DnaSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaSequenceRepository extends JpaRepository<DnaSequence, String> {
    boolean existsByDna(String dna);
    DnaSequence findByDna(String dna);
    long countByIsMutant(boolean isMutant);
}







