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
public class UserInfo {

    // First name of the user
    private String firstName;

    // Last name of the user
    private String lastName;

    // Birth date of the user. In the format YYYY-MM-DD
    private String dateOfBirth;

    // Gender of the user = ["MALE" or "FEMALE" or "UNKNOWN"]
    private String gender;

}
