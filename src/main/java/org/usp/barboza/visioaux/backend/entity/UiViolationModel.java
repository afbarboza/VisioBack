package org.usp.barboza.visioaux.backend.entity;

public class UiViolationModel {
    private String type;
    private float priority;
    private String message;

    private UiViolationModel(String type, float priority, String message) {
        this.type = type;
        this.priority = priority;
        this.message = message;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static UiViolationModel map(float priority, Violation violation) {
        String type = violation.getViolationType();
        String message = violation.getDeveloperMessage();

        float roundedPriority = (float) Math.round(priority * 100) / 100;

        return new UiViolationModel(type, roundedPriority, message);
    }

}
