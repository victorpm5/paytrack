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
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
@ToString
public class CashAccount {

    // IBAN of the cash account
    private String iban;

    // Booked balance in EUR
    private Double balance;

    // Description of the product
    private String productDescription;

}
