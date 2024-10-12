/*package com.example.mutantdetector.services;

import com.example.mutantdetector.domain.entities.DnaSequence;
import com.example.mutantdetector.repositories.DnaSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DnaService {

    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    public boolean isMutant(String[] dna) {
        try {
            // Convertir el array de String a un único String
            String dnaString = String.join(",", dna); // Unir con coma (o cualquier delimitador que prefieras)

            // Verificar si la secuencia de ADN ya existe en la base de datos
            if (dnaSequenceRepository.existsByDna(dnaString)) {
                return dnaSequenceRepository.findByDna(dnaString).isMutant();
            }

            int sequences = 0;
            int n = dna.length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Verificamos si se encuentra una secuencia horizontal
                    if (j <= n - 4 && hasSequence(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3))) {
                        sequences++;
                    }

                    // Verificamos si se encuentra una secuencia vertical
                    if (i <= n - 4 && hasSequence(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                        sequences++;
                    }

                    // Verificamos si se encuentra una secuencia diagonal hacia la derecha
                    if (i <= n - 4 && j <= n - 4 && hasSequence(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                        sequences++;
                    }

                    // Verificamos si se encuentra una secuencia diagonal hacia la izquierda
                    if (i <= n - 4 && j >= 3 && hasSequence(dna[i].charAt(j), dna[i + 1].charAt(j - 1), dna[i + 2].charAt(j - 2), dna[i + 3].charAt(j - 3))) {
                        sequences++;
                    }

                    // Si encontramos más de una secuencia, es mutante
                    if (sequences > 1) {
                        // Guardar la secuencia como mutante
                        DnaSequence dnaSequence = new DnaSequence();
                        dnaSequence.setDna(dnaString); // Almacena como String
                        dnaSequence.setMutant(true);
                        dnaSequenceRepository.save(dnaSequence);
                        return true;
                    }
                }
            }

            // Si no encontramos suficientes secuencias, no es mutante
            DnaSequence dnaSequence = new DnaSequence();
            dnaSequence.setDna(dnaString); // Almacena como String
            dnaSequence.setMutant(false);
            dnaSequenceRepository.save(dnaSequence);

            return false;
        } catch (Exception e) {
            // Aquí puedes registrar el error o lanzar una excepción personalizada
            System.err.println("Error procesando ADN: " + e.getMessage());
            throw new RuntimeException("Error al procesar la solicitud de ADN");
        }
    }

    //Método auxiliar para detectar secuencias iguales
    private boolean hasSequence(char a, char b, char c, char d) {
        return a == b && b == c && c == d;
    }
}*/
package com.example.mutantdetector.services;

import com.example.mutantdetector.domain.entities.DnaSequence;
import com.example.mutantdetector.repositories.DnaSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaService {

    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    public boolean isMutant(String[] dna) {
        try {
            // Convertir el array de String a un único String
            String dnaString = String.join(",", dna); // Unir con coma (o cualquier delimitador que prefieras)

            // Verificar si la secuencia de ADN ya existe en la base de datos
            if (dnaSequenceRepository.existsByDna(dnaString)) {
                return dnaSequenceRepository.findByDna(dnaString).isMutant();
            }

            int sequences = 0;
            int n = dna.length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j <= n - 4) {
                        sequences += checkHorizontal(dna, i, j);
                    }
                    if (i <= n - 4) {
                        sequences += checkVertical(dna, i, j);
                    }
                    if (i <= n - 4 && j <= n - 4) {
                        sequences += checkDiagonalRight(dna, i, j);
                    }
                    if (i <= n - 4 && j >= 3) {
                        sequences += checkDiagonalLeft(dna, i, j);
                    }

                    // Si encontramos más de una secuencia, es mutante
                    if (sequences > 1) {
                        saveDnaSequence(dnaString, true);
                        return true;
                    }
                }
            }

            // Si no encontramos suficientes secuencias, no es mutante
            saveDnaSequence(dnaString, false);
            return false;
        } catch (Exception e) {
            // Aquí puedes registrar el error o lanzar una excepción personalizada
            System.err.println("Error procesando ADN: " + e.getMessage());
            throw new RuntimeException("Error al procesar la solicitud de ADN");
        }
    }

    private int checkHorizontal(String[] dna, int i, int j) {
        return hasSequence(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3)) ? 1 : 0;
    }

    private int checkVertical(String[] dna, int i, int j) {
        return hasSequence(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j)) ? 1 : 0;
    }

    private int checkDiagonalRight(String[] dna, int i, int j) {
        return hasSequence(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3)) ? 1 : 0;
    }

    private int checkDiagonalLeft(String[] dna, int i, int j) {
        return hasSequence(dna[i].charAt(j), dna[i + 1].charAt(j - 1), dna[i + 2].charAt(j - 2), dna[i + 3].charAt(j - 3)) ? 1 : 0;
    }

    private void saveDnaSequence(String dnaString, boolean isMutant) {
        DnaSequence dnaSequence = new DnaSequence();
        dnaSequence.setDna(dnaString); // Almacena como String
        dnaSequence.setMutant(isMutant);
        dnaSequenceRepository.save(dnaSequence);
    }

    // Método auxiliar para detectar secuencias iguales
    private boolean hasSequence(char a, char b, char c, char d) {
        return a == b && b == c && c == d;
    }

    // Métodos para obtener estadísticas
    public long countMutantDna() {
        return dnaSequenceRepository.countByIsMutant(true);
    }

    public long countHumanDna() {
        return dnaSequenceRepository.countByIsMutant(false);
    }
}



