package com.liferay.solarBudget.internal.services;

import java.util.ArrayList;
import java.util.List;


import com.liferay.solarBudget.dto.v1_0.PostalCode;
import com.liferay.solarBudget.dto.v1_0.Property;
import com.liferay.solarBudget.dto.v1_0.Address;
import com.liferay.solarBudget.dto.v1_0.Estate;

public class FakeGeocode {

  public List<PostalCode> getMunicipalities(String postalCode) {
    List<PostalCode> postalCodes = new ArrayList<PostalCode>();

    for(int i=0; i<10; i++) {
		  PostalCode postalCodeData = new PostalCode();
      postalCodeData.setMunicipalityId("186");
      postalCodeData.setMunicipalityName("PIEDRAHITA");
      postalCodeData.setPopulationName("PIEDRAHITA");
      postalCodeData.setPopulationId("186");
      postalCodeData.setProvinceId("05");

      postalCodes.add(postalCodeData);
    }

    return postalCodes;
  }

  public List<Estate> getEstates(String populationId, String addressId) {
    List<Estate> estates = new ArrayList<Estate>();

    for(int i=0; i<10; i++) {
		  Estate estate = new Estate();
      estate.setNumber("1");
      estate.setAnnex("");
      estate.setGateId("P01092126");

      estates.add(estate);
    }

    return estates;
  }

  public List<Address> getAddresses(String populationId, String postalCode) {
    List<Address> addresses = new ArrayList<Address>();

    for(int i=0; i<10; i++) {
		  Address address = new Address();
      address.setKind("");
      address.setName("A (URB HUERTA EL DUQUE)");
      address.setAddressId("DUMMY-ADDRESS");

      addresses.add(address);
    }
    return addresses;
  }
}
