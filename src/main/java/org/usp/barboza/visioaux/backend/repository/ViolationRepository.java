package org.usp.barboza.visioaux.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.usp.barboza.visioaux.backend.entity.Violation;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Integer> {
    @Query(
            value = "SELECT * FROM tb_violation WHERE activity_name = ?1 AND violation_type = ?2 AND developer_message = ?3 AND device_id = ?4",
            nativeQuery = true
    )
    public List<Violation> findViolationIdenticalTo(
            String activityName,
            String violationType,
            String developerMessage,
            String deviceId
    );
}
