package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserAccountStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    SUSPENDED("SUSPENDED");

    /**
     * Map containing the String value of Genders and all the Gender enums
     */
    private static final Map<String, UserAccountStatus> UserAccountStatusMap = new HashMap<>();

    /*
      the static block for loading the Gender map
     */
    static {
        for (UserAccountStatus USERACCOUNTSTATUS : UserAccountStatus.values()) {
            UserAccountStatusMap.put(USERACCOUNTSTATUS.name(), USERACCOUNTSTATUS);
        }
    }

    /**
     * String type field of UserAccountStatus enum for accessing UserAccountStatus enum
     */
    private final String userAccountStatus;

    /**
     * Constructor of the enum
     *
     * @param UserAccountStatus creates UserAccountStatus object for the enum
     */
    UserAccountStatus(String UserAccountStatus) {
        this.userAccountStatus = UserAccountStatus;
    }

    /**
     * Method for getting enum from the String value
     *
     * @param userAccountStatus look for enum present in the map from the String type value
     * @return UserAccountStatus enum
     */
    public static UserAccountStatus fromStatus(String userAccountStatus) {
        if (UserAccountStatusMap.containsKey(userAccountStatus))
            return UserAccountStatusMap.get(userAccountStatus);
        return null;
    }

    /**
     * Getter for the enum values
     *
     * @return String value of the enum
     */
    public String getUserAccountStatus() {
        return userAccountStatus;
    }
}
