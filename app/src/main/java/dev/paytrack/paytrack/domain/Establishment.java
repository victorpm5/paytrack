package dev.paytrack.paytrack.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
@Getter
@Setter
@Builder
@ToString
public class Establishment {

    private String iban;
    private String name;
    private String location;
    private String city;

}
