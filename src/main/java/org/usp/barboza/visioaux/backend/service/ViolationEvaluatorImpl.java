package org.usp.barboza.visioaux.backend.service;

import org.usp.barboza.visioaux.backend.entity.UiViolationModel;
import org.usp.barboza.visioaux.backend.entity.Violation;
import org.usp.barboza.visioaux.backend.entity.ViolationGroupingCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class uses the severity sigma to sort accessibility issues by severity.
 */
public class ViolationEvaluatorImpl implements ViolationEvaluator {
    @Override
    public List<UiViolationModel> sortByPriority(List<Violation> violations) {
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
                .stream()
                .toList();

        // Step 2: for each group of equivalent violations, do
        // Step 2.a: create a flat list of violations
        List<Violation> report = new ArrayList<>();
        List<UiViolationModel> violationsByPriority = new ArrayList<>();

        for (List<Violation> equivalentViolations : grouped) {
            int sumOccurrences = 0;
            Violation firstViolation = equivalentViolations.getFirst();

            // "A", "AA", "AAA"
            int conformanceLevel = firstViolation
                    .getConformanceLevel()
                    .length();

            //  Step 2.b: sum the number of occurences between them
            for (Violation v : equivalentViolations) {
                sumOccurrences += v.getNumberOccurrences();
            }

            //  Step 2.c: multiply by the number of users facing the issue
            int numberOfUsers = equivalentViolations.size();
            int frequency = numberOfUsers * sumOccurrences;

            //  Step 2.d: divide by their level of conformance
            float priority = frequency / (1.0f * conformanceLevel);

            // violationsByPriority.add(Pair.of(priority, firstViolation));
            violationsByPriority.add(
                    UiViolationModel.map(priority, firstViolation)
            );
        }

        // Step 3: Rank the initial group of equivalent violations
        violationsByPriority.sort((o1, o2) -> {
            float priorityOne = o1.getPriority();
            float priorityTwo = o2.getPriority();
            return Float.compare(priorityTwo, priorityOne);
        });

        return violationsByPriority;
    }
}
