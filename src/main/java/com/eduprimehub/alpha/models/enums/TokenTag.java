package com.eduprimehub.alpha.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum TokenTag {
    TOKEN("TOKEN"),
    TIMEOUT("TIMEOUT"),
    TIMEOUT_UNIT("TIMEOUT_UNIT");

    /**
     * Map containing the String value of TokenTags and all the TokenTag enums
     */
    private static final Map<String, TokenTag> tokenTagMap = new HashMap<>();

    /*
      the static block for loading the TokenTag map
     */
    static {
        for (TokenTag tokenTag : TokenTag.values()) {
            tokenTagMap.put(tokenTag.name(), tokenTag);
        }
    }

    /**
     * String type field of TokenTag enum for accessing TokenTag enum
     */
    private final String tokenTag;

    /**
     * Constructor of the enum
     *
     * @param tokenTag creates TokenTag object for the enum
     */
    TokenTag(String tokenTag) {
        this.tokenTag = tokenTag;
    }

    /**
     * Method for getting enum from the String value
     *
     * @param tokenTag look for enum present in the map from the String type value
     * @return TokenTag enum
     */
    public static TokenTag fromTokenTag(String tokenTag) {

        if (tokenTagMap.containsKey(tokenTag))
            return tokenTagMap.get(tokenTag);

        return null;
    }

    /**
     * Getter for the enum values
     *
     * @return String value of the enum
     */
    public String getTokenTag() {
        return tokenTag;
    }
}


