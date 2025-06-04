package org.usp.barboza.visioaux.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.usp.barboza.visioaux.backend.entity.Violation;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Integer> {
    @Query(
            value = "SELECT * FROM tb_violation WHERE app_package_id = ?1 AND activity_name = ?2 AND violation_type = ?3 AND developer_message = ?4 AND device_id = ?5",
            nativeQuery = true
    )
    public List<Violation> findViolationIdenticalTo(
            String appId,
            String activityName,
            String violationType,
            String developerMessage,
            String deviceId
    );

    @Query(
            value = "SELECT * FROM tb_violation WHERE pp_package_id = ?1",
            nativeQuery = true
    )
    public List<Violation> findAllByAppId(String appId);
}
