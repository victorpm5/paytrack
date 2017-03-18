package dev.paytrack.paytrack.enums;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");

    private String literal;

    Gender(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }
}
