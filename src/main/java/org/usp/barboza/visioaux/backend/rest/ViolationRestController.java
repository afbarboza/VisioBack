package org.usp.barboza.visioaux.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.usp.barboza.visioaux.backend.entity.Violation;
import org.usp.barboza.visioaux.backend.service.ViolationService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ViolationRestController {
    private ViolationService violationService;

    @Autowired
    public ViolationRestController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("/violation")
    public List<Violation> findAll() {
        return violationService.findAll();
    }

    @GetMapping("/violation/{violationId}")
    public Violation getViolation(@PathVariable int violationId) {
        Violation violation = violationService.findById(violationId);

        if (violation == null) {
            throw new RuntimeException("Violation with id " + violationId + " not found");
        }

        return violation;
    }

    @PostMapping("/violation")
    public Violation addViolation(@RequestBody Violation violation) {
        List<Violation> equivalentViolations = violationService.findViolationEquivalentTo(violation);
        if (equivalentViolations != null && !equivalentViolations.isEmpty()) {
            Violation equivalentViolation = equivalentViolations.getFirst();
            int occurrences = equivalentViolation.getNumberOccurrences();
            equivalentViolation.setNumberOccurrences(occurrences + 1);
            return violationService.save(equivalentViolation);
        } else {
            violation.setId(0);
            violation.setNumberOccurrences(1);
            return violationService.save(violation);
        }
    }

    @PutMapping("/violation")
    public Violation updateViolation(@RequestBody Violation violation) {
        return violationService.save(violation);
    }

    @DeleteMapping("/violation/{violationId}")
    public String deleteViolation(@PathVariable int violationId) {
        Violation tmpViolation = violationService.findById(violationId);

        if (tmpViolation == null) {
            throw new RuntimeException("Violation with id " + violationId + " not found. Couldn't delete it");
        }

        violationService.deleteById(violationId);
        return "Deleted violation with id " + violationId;
    }
}
