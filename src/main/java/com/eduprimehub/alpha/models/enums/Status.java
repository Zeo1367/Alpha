package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    /**
     * Map containing the String value of Genders and all the Gender enums
     */
    private static final Map<String, Status> statusMap = new HashMap<>();

    /*
      the static block for loading the Gender map
     */
    static {
        for (Status status : Status.values()) {
            statusMap.put(status.name(), status);
        }
    }

    /**
     * String type field of Gender enum for accessing Gender enum
     */
    private final String status;

    /**
     * Constructor of the enum
     *
     * @param status creates Gender object for the enum
     */
    Status(String status) {
        this.status = status;
    }

    /**
     * Method for getting enum from the String value
     *
     * @param status look for enum present in the map from the String type value
     * @return Status enum
     */
    public static Status fromStatus(String status) {
        if (statusMap.containsKey(status))
            return statusMap.get(status);
        return null;
    }

    /**
     * Getter for the enum values
     *
     * @return String value of the enum
     */
    public String getStatus() {
        return status;
    }
}
