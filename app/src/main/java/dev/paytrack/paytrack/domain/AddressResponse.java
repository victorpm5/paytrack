package dev.paytrack.paytrack.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private List<Address> addressList;

    public List<Address> getAddressList() {
        return addressList;
    }
}
