package com.edms.backend.entity;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StationEnum {
    Minor("Minor"),
    Major("Major");

    private final String value;

    StationEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static StationEnum fromString(String key) {
        System.out.println("Received station type for conversion: " + key); // 🔍 Debug log

        if (key == null) {
            System.out.println("Station type is null!");
            return null;
        }
        
        for (StationEnum type : StationEnum.values()) {
            System.out.println("Comparing with: " + type.getValue());
            if (type.getValue().equalsIgnoreCase(key.trim())) { // ✅ Trim spaces
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid Station Type: " + key);
    }
}

