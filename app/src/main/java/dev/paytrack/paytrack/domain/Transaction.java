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

    public String getOriginIban() {
        return originIban;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCounterPartyName() {
        return counterPartyName;
    }

    public String getCounterPartyIban() {
        return counterPartyIban;
    }

    public String getUsage() {
        return usage;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setOriginIban(String originIban) {
        this.originIban = originIban;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCounterPartyName(String counterPartyName) {
        this.counterPartyName = counterPartyName;
    }

    public void setCounterPartyIban(String counterPartyIban) {
        this.counterPartyIban = counterPartyIban;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
