package com.liferay.gasBudget.internal.dto;

import java.util.*;

public class AddressesResponse {

    List<AddressResponse> addressResponses;

    public List<AddressResponse> getAddressResponses() {
        return addressResponses;
    }

    public void setAddressResponses(
        List<AddressResponse> addressResponses) {
        this.addressResponses = addressResponses;
    }
}
