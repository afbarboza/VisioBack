package org.usp.barboza.visioaux.backend.entity;

public record ViolationGroupingCriteria(
        String activityName,
        String violationType,
        String developerMessage
) {
}
