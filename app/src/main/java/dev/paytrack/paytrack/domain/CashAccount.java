package dev.paytrack.paytrack.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class CashAccount {

    // IBAN of the cash account
    private String iban;

    // Booked balance in EUR
    private Number balance;

    // Description of the product
    private String productDescription;

}
