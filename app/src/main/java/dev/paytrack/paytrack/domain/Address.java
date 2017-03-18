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
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
@ToString
public class Address {

    // Street
    private String street;

    // House number
    private String houseNumber;

    // Zip code
    private String zip;

    // City
    private String city;

    // Country
    private String country;

    // Type of address = ["MAILING_ADDRESS" or "REGISTRATION_ADDRESS"]
    private String type;

}
