package org.usp.barboza.visioaux.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.usp.barboza.visioaux.backend.entity.Violation;
import org.usp.barboza.visioaux.backend.service.ViolationService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageRestController {
    private ViolationService violationService;

    @Autowired
    public MessageRestController(ViolationService violationService) {
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
        violation.setId(0);
        Violation dbViolation = violationService.save(violation);
        return dbViolation;
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
