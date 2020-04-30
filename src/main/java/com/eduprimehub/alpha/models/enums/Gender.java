package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;


public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    /**
     * String type field of UserType enum for accessing userType enum
     */
    private String gender;

    /**
     * Map containing the String value of UserTypes and all the userType enums
     */
    private static Map<String, Gender> genderMap = new HashMap<>();

    /**
     * static block for loading the UserType map
     */
    static {
        for (Gender Gender : Gender.values()) {
            genderMap.put(Gender.name(), Gender);
        }
    }

    /**
     * Constructor of the enum
     *
     * @param gender creates UserType object for the enum
     */
    Gender(String gender) {
        this.gender = gender;
    }

    /**
     * @param gender look for enum present in the map from the String type value
     * @return UserType enum
     */
    public static Gender fromGender(String gender) {

        if (genderMap.containsKey(gender))
            return genderMap.get(gender);

        return null;
    }

    public String getGender() {
        return gender;
    }
}
