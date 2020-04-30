package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * UserType enum
 */
public enum UserType {

    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN"),
    GROUP("GROUP"),
    FIRM("FIRM");

    /**
     * String type field of UserType enum for accessing userType enum
     */
    private String userType;

    /**
     * Map containing the String value of UserTypes and all the userType enums
     */
    private static Map<String, UserType> UserTypeMap = new HashMap<>();

    /**
     * static block for loading the UserType map
     */
    static {
        for (UserType UserType : UserType.values()) {
            UserTypeMap.put(UserType.name(), UserType);
        }
    }

    /**
     * Constructor of the enum
     *
     * @param userType creates UserType object for the enum
     */
    UserType(String userType) {
        this.userType = userType;
    }

    /**
     * @param name look for enum present in the map from the String type value
     * @return UserType enum
     */
    public static UserType fromUserType(String name) {

        if (UserTypeMap.containsKey(name))
            return UserTypeMap.get(name);

        return null;
    }

    /**
     * getter of UserType enum
     *
     * @return String value of UserType enum
     */
    public String getUserType() {
        return userType;
    }


    @Override
    public String toString() {
        return "UserType{" +
                "UserType='" + userType + '\'' +
                '}';
    }
}
