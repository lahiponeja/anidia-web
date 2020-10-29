package com.liferay.gasComparator.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
@GraphQLName("GasConsumptionByUse")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "GasConsumptionByUse")
public class GasConsumptionByUse {

	@GraphQLName("AcsUse")
	public static enum AcsUse {

		BUTANO("Butano"), GLP("GLP"), GOC("GOC"), ELECTRICIDAD("Electricidad");

		@JsonCreator
		public static AcsUse create(String value) {
			for (AcsUse acsUse : values()) {
				if (Objects.equals(acsUse.getValue(), value)) {
					return acsUse;
				}
			}

			return null;
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private AcsUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("HeatingUse")
	public static enum HeatingUse {

		BUTANO("Butano"), GLP("GLP"), GOC("GOC"), ELECTRICIDAD("Electricidad");

		@JsonCreator
		public static HeatingUse create(String value) {
			for (HeatingUse heatingUse : values()) {
				if (Objects.equals(heatingUse.getValue(), value)) {
					return heatingUse;
				}
			}

			return null;
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private HeatingUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("KitchenUse")
	public static enum KitchenUse {

		BUTANO("Butano"), GLP("GLP"), GOC("GOC"), ELECTRICIDAD("Electricidad");

		@JsonCreator
		public static KitchenUse create(String value) {
			for (KitchenUse kitchenUse : values()) {
				if (Objects.equals(kitchenUse.getValue(), value)) {
					return kitchenUse;
				}
			}

			return null;
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private KitchenUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Schema(
		description = "Water heating is individual, not collective from the building"
	)
	public Boolean getAcsIndividual() {
		return acsIndividual;
	}

	public void setAcsIndividual(Boolean acsIndividual) {
		this.acsIndividual = acsIndividual;
	}

	@JsonIgnore
	public void setAcsIndividual(
		UnsafeSupplier<Boolean, Exception> acsIndividualUnsafeSupplier) {

		try {
			acsIndividual = acsIndividualUnsafeSupplier.get();
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
	protected Boolean acsIndividual;

	@Schema(description = "Kind of energy used for water heating")
	@Valid
	public AcsUse getAcsUse() {
		return acsUse;
	}

	@JsonIgnore
	public String getAcsUseAsString() {
		if (acsUse == null) {
			return null;
		}

		return acsUse.toString();
	}

	public void setAcsUse(AcsUse acsUse) {
		this.acsUse = acsUse;
	}

	@JsonIgnore
	public void setAcsUse(
		UnsafeSupplier<AcsUse, Exception> acsUseUnsafeSupplier) {

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
	protected AcsUse acsUse;

	@Schema(
		description = "Heating is individual, not collective from the building"
	)
	public Boolean getHeatingIndividual() {
		return heatingIndividual;
	}

	public void setHeatingIndividual(Boolean heatingIndividual) {
		this.heatingIndividual = heatingIndividual;
	}

	@JsonIgnore
	public void setHeatingIndividual(
		UnsafeSupplier<Boolean, Exception> heatingIndividualUnsafeSupplier) {

		try {
			heatingIndividual = heatingIndividualUnsafeSupplier.get();
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
	protected Boolean heatingIndividual;

	@Schema(description = "Kind of energy used for heating")
	@Valid
	public HeatingUse getHeatingUse() {
		return heatingUse;
	}

	@JsonIgnore
	public String getHeatingUseAsString() {
		if (heatingUse == null) {
			return null;
		}

		return heatingUse.toString();
	}

	public void setHeatingUse(HeatingUse heatingUse) {
		this.heatingUse = heatingUse;
	}

	@JsonIgnore
	public void setHeatingUse(
		UnsafeSupplier<HeatingUse, Exception> heatingUseUnsafeSupplier) {

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
	protected HeatingUse heatingUse;

	@Schema(description = "Kind of energy used for the kitchen")
	@Valid
	public KitchenUse getKitchenUse() {
		return kitchenUse;
	}

	@JsonIgnore
	public String getKitchenUseAsString() {
		if (kitchenUse == null) {
			return null;
		}

		return kitchenUse.toString();
	}

	public void setKitchenUse(KitchenUse kitchenUse) {
		this.kitchenUse = kitchenUse;
	}

	@JsonIgnore
	public void setKitchenUse(
		UnsafeSupplier<KitchenUse, Exception> kitchenUseUnsafeSupplier) {

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
	protected KitchenUse kitchenUse;

	@Schema(description = "Wether the house is in the last floor")
	public Boolean getLastFloor() {
		return lastFloor;
	}

	public void setLastFloor(Boolean lastFloor) {
		this.lastFloor = lastFloor;
	}

	@JsonIgnore
	public void setLastFloor(
		UnsafeSupplier<Boolean, Exception> lastFloorUnsafeSupplier) {

		try {
			lastFloor = lastFloorUnsafeSupplier.get();
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
	protected Boolean lastFloor;

	@Schema(description = "Number of people living in the house")
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	@JsonIgnore
	public void setNumberOfPeople(
		UnsafeSupplier<Integer, Exception> numberOfPeopleUnsafeSupplier) {

		try {
			numberOfPeople = numberOfPeopleUnsafeSupplier.get();
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
	protected Integer numberOfPeople;

	@Schema(description = "Province of the user")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@JsonIgnore
	public void setProvince(
		UnsafeSupplier<String, Exception> provinceUnsafeSupplier) {

		try {
			province = provinceUnsafeSupplier.get();
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
	protected String province;

	@Schema(description = "There's only a family in the house")
	public Boolean getSingleFamilyHouse() {
		return singleFamilyHouse;
	}

	public void setSingleFamilyHouse(Boolean singleFamilyHouse) {
		this.singleFamilyHouse = singleFamilyHouse;
	}

	@JsonIgnore
	public void setSingleFamilyHouse(
		UnsafeSupplier<Boolean, Exception> singleFamilyHouseUnsafeSupplier) {

		try {
			singleFamilyHouse = singleFamilyHouseUnsafeSupplier.get();
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
	protected Boolean singleFamilyHouse;

	@Schema(description = "Area of the house in m2")
	public String getSurfaceHouse() {
		return surfaceHouse;
	}

	public void setSurfaceHouse(String surfaceHouse) {
		this.surfaceHouse = surfaceHouse;
	}

	@JsonIgnore
	public void setSurfaceHouse(
		UnsafeSupplier<String, Exception> surfaceHouseUnsafeSupplier) {

		try {
			surfaceHouse = surfaceHouseUnsafeSupplier.get();
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
	protected String surfaceHouse;

	@Schema(description = "Number of times the kitcheen is used in a week")
	public Integer getWeeklyKitchenUse() {
		return weeklyKitchenUse;
	}

	public void setWeeklyKitchenUse(Integer weeklyKitchenUse) {
		this.weeklyKitchenUse = weeklyKitchenUse;
	}

	@JsonIgnore
	public void setWeeklyKitchenUse(
		UnsafeSupplier<Integer, Exception> weeklyKitchenUseUnsafeSupplier) {

		try {
			weeklyKitchenUse = weeklyKitchenUseUnsafeSupplier.get();
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
	protected Integer weeklyKitchenUse;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GasConsumptionByUse)) {
			return false;
		}

		GasConsumptionByUse gasConsumptionByUse = (GasConsumptionByUse)object;

		return Objects.equals(toString(), gasConsumptionByUse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (acsIndividual != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"acsIndividual\": ");

			sb.append(acsIndividual);
		}

		if (acsUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"acsUse\": ");

			sb.append("\"");

			sb.append(acsUse);

			sb.append("\"");
		}

		if (heatingIndividual != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"heatingIndividual\": ");

			sb.append(heatingIndividual);
		}

		if (heatingUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"heatingUse\": ");

			sb.append("\"");

			sb.append(heatingUse);

			sb.append("\"");
		}

		if (kitchenUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"kitchenUse\": ");

			sb.append("\"");

			sb.append(kitchenUse);

			sb.append("\"");
		}

		if (lastFloor != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastFloor\": ");

			sb.append(lastFloor);
		}

		if (numberOfPeople != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberOfPeople\": ");

			sb.append(numberOfPeople);
		}

		if (province != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"province\": ");

			sb.append("\"");

			sb.append(_escape(province));

			sb.append("\"");
		}

		if (singleFamilyHouse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"singleFamilyHouse\": ");

			sb.append(singleFamilyHouse);
		}

		if (surfaceHouse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"surfaceHouse\": ");

			sb.append("\"");

			sb.append(_escape(surfaceHouse));

			sb.append("\"");
		}

		if (weeklyKitchenUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"weeklyKitchenUse\": ");

			sb.append(weeklyKitchenUse);
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