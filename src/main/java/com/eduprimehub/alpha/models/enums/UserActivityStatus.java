package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserActivityStatus {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE"),
    BUSY("BUSY");

    /**
     * Map containing the String value of UserActivityStatus and all the UserActivityStatus enums
     */
    private static final Map<String, UserActivityStatus> userActivityStatusMap = new HashMap<>();

    /*
      the static block for loading the UserActivityStatus map
     */
    static {
        for (UserActivityStatus USERACCOUNTSTATUS : UserActivityStatus.values()) {
            userActivityStatusMap.put(USERACCOUNTSTATUS.name(), USERACCOUNTSTATUS);
        }
    }

    /**
     * String type field of Gender enum for accessing Gender enum
     */
    private final String userActivityStatus;

    /**
     * Constructor of the enum
     *
     * @param userActivityStatus creates UserActivityStatus object for the enum
     */
    UserActivityStatus(String userActivityStatus) {
        this.userActivityStatus = userActivityStatus;
    }

    /**
     * Method for getting enum from the String value
     *
     * @param userActivityStatus look for enum present in the map from the String type value
     * @return UserActivityStatus enum
     */
    public static UserActivityStatus fromStatus(String userActivityStatus) {
        if (userActivityStatusMap.containsKey(userActivityStatus))
            return userActivityStatusMap.get(userActivityStatus);
        return null;
    }

    /**
     * Getter for the enum values
     *
     * @return String value of the enum
     */
    public String getUserActivityStatus() {
        return userActivityStatus;
    }
}
