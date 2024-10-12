package com.example.mutantdetector.presentation.rest;

import com.example.mutantdetector.services.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private DnaService dnaService;

    @GetMapping
    public Map<String, Object> getStats() {
        long countMutantDna = dnaService.countMutantDna();
        long countHumanDna = dnaService.countHumanDna();
        double ratio = countHumanDna > 0 ? (double) countMutantDna / countHumanDna : 0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", countMutantDna);
        stats.put("count_human_dna", countHumanDna);
        stats.put("ratio", ratio);

        return stats;
    }
}


