package org.usp.barboza.visioaux.backend.service;

import org.usp.barboza.visioaux.backend.entity.UiViolationModel;
import org.usp.barboza.visioaux.backend.entity.Violation;

import java.util.List;

/**
 * Implement this interface when you want to change the sorting algorith to prioritze accessibility issues
 */
public interface ViolationEvaluator {
    public List<UiViolationModel> sortByPriority(List<Violation> violations);
}
