package org.usp.barboza.visioaux.backend.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_violation")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "app_package_id")
    private String appId;

    @Column(name = "violation_type")
    private String violationType;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "conformance_level")
    private String conformanceLevel;

    @Column(name = "developer_message")
    private String developerMessage;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "number_occurrences")
    private int numberOccurrences;

    public Violation(int id, String violationType, String activityName, String conformanceLevel, String developerMessage, String deviceId, int numberOccurrences) {
        this.id = id;
        this.violationType = violationType;
        this.activityName = activityName;
        this.conformanceLevel = conformanceLevel;
        this.developerMessage = developerMessage;
        this.deviceId = deviceId;
        this.numberOccurrences = numberOccurrences;
    }

    public Violation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getConformanceLevel() {
        return conformanceLevel;
    }

    public void setConformanceLevel(String conformanceLevel) {
        this.conformanceLevel = conformanceLevel;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getNumberOccurrences() {
        return numberOccurrences;
    }

    public void setNumberOccurrences(int numberOccurrences) {
        this.numberOccurrences = numberOccurrences;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
