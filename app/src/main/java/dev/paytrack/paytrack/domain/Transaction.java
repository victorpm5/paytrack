package dev.paytrack.paytrack.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
@Getter
@Setter
@Builder
@ToString
public class Transaction {

    // The IBAN of this account
    private String originIban;

    // Amount of the transaction. If the amount is positive, the customer received money,
    // if the amount is negative the customer lost money
    private Double amount;

    // Name of the counter party (optional)
    private String counterPartyName;

    // IBAN of the counter party (optional)
    private String counterPartyIban;

    // Payment reference
    private String usage;

    // Booking date. In the format YYYY-MM-DD
    private String bookingDate;

}
