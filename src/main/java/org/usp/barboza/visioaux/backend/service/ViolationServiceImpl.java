package org.usp.barboza.visioaux.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usp.barboza.visioaux.backend.entity.Violation;
import org.usp.barboza.visioaux.backend.entity.ViolationGroupingCriteria;
import org.usp.barboza.visioaux.backend.repository.ViolationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViolationServiceImpl implements ViolationService {

    private ViolationRepository violationRepository;

    @Autowired
    public ViolationServiceImpl(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    @Override
    public List<Violation> findAll() {
        return violationRepository.findAll();
    }

    @Override
    public List<Violation> findViolationIdenticalTo(Violation violation) {
        return violationRepository.findViolationIdenticalTo(
                violation.getActivityName(),
                violation.getViolationType(),
                violation.getDeveloperMessage(),
                violation.getDeviceId()
        );
    }

    @Override
    public Violation findById(int id) {
        Violation violation;
        Optional<Violation> result = violationRepository.findById(id);

        if (result.isPresent()) {
            violation = result.get();
        } else {
            throw new RuntimeException("Couldn't find message with id: " + id);
        }

        return violation;
    }

    @Override
    public Violation save(Violation violation) {
        return violationRepository.save(violation);
    }

    @Override
    public void deleteById(int id) {
        violationRepository.deleteById(id);
    }

    @Override
    public List<Violation> findAllByPriority() {
        // TODO Step 3: Find all the violations
        // TODO and sort them by priority

        // Step 0: get all violations
        List<Violation> violations = violationRepository.findAll();

        // Step 1: group equivalent violations (list of lists?)
        List<List<Violation>> grouped = violations
                .stream()
                .collect(Collectors
                    .groupingBy(violation ->
                        new ViolationGroupingCriteria(
                            violation.getActivityName(),
                            violation.getViolationType(),
                            violation.getDeveloperMessage()
                        )
                    )
                )
                .values()
                .stream().
                toList();

        // Step 2: for each group of equivalent violations, do
        // Step 2.a: create a flat list of violations
        List<Violation> report = new ArrayList<>();
        for (List<Violation> equivalentViolations : grouped) {
            Violation first = equivalentViolations.getFirst();
            report.add(first);
        }

        //  Step 2.b: sum the number of occurences between them

        //  Step 2.c: multiply by the number of users facing the issue

        //  Step 2.d: divide by their level of conformance

        // Step 3: Rank the initial group of equivalent violations

        return report;
    }
}
