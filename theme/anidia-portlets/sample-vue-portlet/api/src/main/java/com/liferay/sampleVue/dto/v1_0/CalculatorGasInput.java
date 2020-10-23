package com.liferay.sampleVue.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@GraphQLName("CalculatorGasInput")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "CalculatorGasInput")
public class CalculatorGasInput {

	@Schema(description = "ACSUse calculator gas")
	public String getAcsUse() {
		return acsUse;
	}

	public void setAcsUse(String acsUse) {
		this.acsUse = acsUse;
	}

	@JsonIgnore
	public void setAcsUse(
		UnsafeSupplier<String, Exception> acsUseUnsafeSupplier) {

		try {
			acsUse = acsUseUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String acsUse;

	@Schema(description = "BathroomNumber calculator gas")
	public String getBathroomNumber() {
		return bathroomNumber;
	}

	public void setBathroomNumber(String bathroomNumber) {
		this.bathroomNumber = bathroomNumber;
	}

	@JsonIgnore
	public void setBathroomNumber(
		UnsafeSupplier<String, Exception> bathroomNumberUnsafeSupplier) {

		try {
			bathroomNumber = bathroomNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String bathroomNumber;

	@Schema(description = "BoilerLocation calculator gas")
	public String getBoilerLocation() {
		return boilerLocation;
	}

	public void setBoilerLocation(String boilerLocation) {
		this.boilerLocation = boilerLocation;
	}

	@JsonIgnore
	public void setBoilerLocation(
		UnsafeSupplier<String, Exception> boilerLocationUnsafeSupplier) {

		try {
			boilerLocation = boilerLocationUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String boilerLocation;

	@Schema(description = "Extras calculator gas")
	@Valid
	public CalculatorGasInputExtras getExtras() {
		return extras;
	}

	public void setExtras(CalculatorGasInputExtras extras) {
		this.extras = extras;
	}

	@JsonIgnore
	public void setExtras(
		UnsafeSupplier<CalculatorGasInputExtras, Exception>
			extrasUnsafeSupplier) {

		try {
			extras = extrasUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected CalculatorGasInputExtras extras;

	@Schema(description = "FloorNumber calculator gas")
	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	@JsonIgnore
	public void setFloorNumber(
		UnsafeSupplier<String, Exception> floorNumberUnsafeSupplier) {

		try {
			floorNumber = floorNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String floorNumber;

	@Schema(description = "GasNaturalUse calculator gas")
	public String getGasNaturalUse() {
		return gasNaturalUse;
	}

	public void setGasNaturalUse(String gasNaturalUse) {
		this.gasNaturalUse = gasNaturalUse;
	}

	@JsonIgnore
	public void setGasNaturalUse(
		UnsafeSupplier<String, Exception> gasNaturalUseUnsafeSupplier) {

		try {
			gasNaturalUse = gasNaturalUseUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String gasNaturalUse;

	@Schema(description = "HeatingUse calculator gas")
	public String getHeatingUse() {
		return heatingUse;
	}

	public void setHeatingUse(String heatingUse) {
		this.heatingUse = heatingUse;
	}

	@JsonIgnore
	public void setHeatingUse(
		UnsafeSupplier<String, Exception> heatingUseUnsafeSupplier) {

		try {
			heatingUse = heatingUseUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String heatingUse;

	@Schema(description = "Housetype calculator gas")
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	@JsonIgnore
	public void setHouseType(
		UnsafeSupplier<String, Exception> houseTypeUnsafeSupplier) {

		try {
			houseType = houseTypeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String houseType;

	@Schema(description = "KitchenUse calculator gas")
	public String getKitchenUse() {
		return kitchenUse;
	}

	public void setKitchenUse(String kitchenUse) {
		this.kitchenUse = kitchenUse;
	}

	@JsonIgnore
	public void setKitchenUse(
		UnsafeSupplier<String, Exception> kitchenUseUnsafeSupplier) {

		try {
			kitchenUse = kitchenUseUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String kitchenUse;

	@Schema(description = "PersonsWater calculator gas")
	public String getPersonsWater() {
		return personsWater;
	}

	public void setPersonsWater(String personsWater) {
		this.personsWater = personsWater;
	}

	@JsonIgnore
	public void setPersonsWater(
		UnsafeSupplier<String, Exception> personsWaterUnsafeSupplier) {

		try {
			personsWater = personsWaterUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String personsWater;

	@Schema(description = "Property meters calculator gas")
	public String getPropertyMeters() {
		return propertyMeters;
	}

	public void setPropertyMeters(String propertyMeters) {
		this.propertyMeters = propertyMeters;
	}

	@JsonIgnore
	public void setPropertyMeters(
		UnsafeSupplier<String, Exception> propertyMetersUnsafeSupplier) {

		try {
			propertyMeters = propertyMetersUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String propertyMeters;

	@Schema(description = "StaysNumber calculator gas")
	public String getStaysNumber() {
		return staysNumber;
	}

	public void setStaysNumber(String staysNumber) {
		this.staysNumber = staysNumber;
	}

	@JsonIgnore
	public void setStaysNumber(
		UnsafeSupplier<String, Exception> staysNumberUnsafeSupplier) {

		try {
			staysNumber = staysNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String staysNumber;

	@Schema(description = "Zipcode calculator gas")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonIgnore
	public void setZipCode(
		UnsafeSupplier<String, Exception> zipCodeUnsafeSupplier) {

		try {
			zipCode = zipCodeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String zipCode;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CalculatorGasInput)) {
			return false;
		}

		CalculatorGasInput calculatorGasInput = (CalculatorGasInput)object;

		return Objects.equals(toString(), calculatorGasInput.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (acsUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"acsUse\": ");

			sb.append("\"");

			sb.append(_escape(acsUse));

			sb.append("\"");
		}

		if (bathroomNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bathroomNumber\": ");

			sb.append("\"");

			sb.append(_escape(bathroomNumber));

			sb.append("\"");
		}

		if (boilerLocation != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"boilerLocation\": ");

			sb.append("\"");

			sb.append(_escape(boilerLocation));

			sb.append("\"");
		}

		if (extras != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"extras\": ");

			sb.append(String.valueOf(extras));
		}

		if (floorNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"floorNumber\": ");

			sb.append("\"");

			sb.append(_escape(floorNumber));

			sb.append("\"");
		}

		if (gasNaturalUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gasNaturalUse\": ");

			sb.append("\"");

			sb.append(_escape(gasNaturalUse));

			sb.append("\"");
		}

		if (heatingUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"heatingUse\": ");

			sb.append("\"");

			sb.append(_escape(heatingUse));

			sb.append("\"");
		}

		if (houseType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"houseType\": ");

			sb.append("\"");

			sb.append(_escape(houseType));

			sb.append("\"");
		}

		if (kitchenUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"kitchenUse\": ");

			sb.append("\"");

			sb.append(_escape(kitchenUse));

			sb.append("\"");
		}

		if (personsWater != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"personsWater\": ");

			sb.append("\"");

			sb.append(_escape(personsWater));

			sb.append("\"");
		}

		if (propertyMeters != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"propertyMeters\": ");

			sb.append("\"");

			sb.append(_escape(propertyMeters));

			sb.append("\"");
		}

		if (staysNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"staysNumber\": ");

			sb.append("\"");

			sb.append(_escape(staysNumber));

			sb.append("\"");
		}

		if (zipCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"zipCode\": ");

			sb.append("\"");

			sb.append(_escape(zipCode));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}