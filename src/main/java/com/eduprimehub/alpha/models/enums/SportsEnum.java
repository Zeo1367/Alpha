package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;


public enum SportsEnum {
    ALL("ALL"),
    CRICKET("CRICKET"),
    SOCCER("SOCCER"),
    RUGBY("RUGBY");

    /**
     * Map containing the String value of Sports and all the Sport enums
     */
    private static final Map<String, SportsEnum> sportMap = new HashMap<>();

    /*
      the static block for loading the Sport map
     */
    static {
        for (SportsEnum sportsEnum : SportsEnum.values()) {
            sportMap.put(sportsEnum.name(), sportsEnum);
        }
    }

    /**
     * String type field of Sport enum for accessing Sport enum
     */
    private final String sport;

    /**
     * Constructor of the enum
     *
     * @param sport creates Sport object for the enum
     */
    SportsEnum(String sport) {
        this.sport = sport;
    }

    /**
     * Method for getting enum from the String value
     *
     * @param sport look for enum present in the map from the String type value
     * @return Sport enum
     */
    public static SportsEnum fromSport(String sport) {

        if (sportMap.containsKey(sport))
            return sportMap.get(sport);

        return null;
    }

    /**
     * Getter for the enum values
     *
     * @return String value of the enum
     */
    public String getSport() {
        return sport;
    }
}
