package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue  //maps the enum value to the database
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Status fromValue(String text) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + text);
    }
}
