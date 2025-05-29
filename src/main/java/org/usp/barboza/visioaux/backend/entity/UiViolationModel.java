package org.usp.barboza.visioaux.backend.entity;

public class UiViolationModel {
    private String type;
    private float priority;
    private String message;
    private String screenName;

    private UiViolationModel(String type, float priority, String message, String screenName) {
        this.type = type;
        this.priority = priority;
        this.message = message;
        this.screenName = screenName;
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

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public static UiViolationModel map(float priority, Violation violation) {
        String type = violation.getViolationType();
        String message = violation.getDeveloperMessage();

        float roundedPriority = (float) Math.round(priority * 100) / 100;

        return new UiViolationModel(type, roundedPriority, message, violation.getActivityName());
    }

}
