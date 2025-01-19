package org.usp.barboza.visioaux.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.usp.barboza.visioaux.backend.entity.Violation;

public interface ViolationRepository extends JpaRepository<Violation, Integer> {
}
