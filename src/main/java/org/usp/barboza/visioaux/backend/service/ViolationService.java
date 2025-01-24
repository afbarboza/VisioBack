package org.usp.barboza.visioaux.backend.service;

import org.usp.barboza.visioaux.backend.entity.Violation;

import java.util.List;

public interface ViolationService {
    List<Violation> findAll();

    List<Violation> findViolationIdenticalTo(Violation violation);

    Violation findById(int id);

    Violation save(Violation violation);

    void deleteById(int id);

    List<Violation> findAllByPriority();
}
