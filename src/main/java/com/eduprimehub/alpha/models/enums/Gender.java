package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;


public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    /**
     * Map containing the String value of Genders and all the Gender enums
     */
    private static final Map<String, Gender> genderMap = new HashMap<>();

    /*
      the static block for loading the Gender map
     */
    static {
        for (Gender gender : Gender.values()) {
            genderMap.put(gender.name(), gender);
        }
    }

    /**
     * String type field of Gender enum for accessing Gender enum
     */
    private final String gender;

    /**
     * Constructor of the enum
     *
     * @param gender creates Gender object for the enum
     */
    Gender(String gender) {
        this.gender = gender;
    }

    /**
     * Method for getting enum from the String value
     *
     * @param gender look for enum present in the map from the String type value
     * @return Gender enum
     */
    public static Gender fromGender(String gender) {

        if (genderMap.containsKey(gender))
            return genderMap.get(gender);

        return null;
    }

    /**
     * Getter for the enum values
     *
     * @return String value of the enum
     */
    public String getGender() {
        return gender;
    }
}
