package dev.paytrack.paytrack.enums;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public enum AddressType {

    MAILING_ADDRESS("MAILING_ADDRESS"),
    REGISTRATION_ADDRESS("REGISTRATION_ADDRESS");

    private String literal;

    AddressType(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }
}
