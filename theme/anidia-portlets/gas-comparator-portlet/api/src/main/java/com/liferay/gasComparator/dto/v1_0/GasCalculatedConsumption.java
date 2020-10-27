package com.liferay.gasComparator.dto.v1_0;

import com.fasterxml.jackson.annotation.*;
import com.liferay.petra.function.*;
import com.liferay.petra.string.*;
import com.liferay.portal.vulcan.graphql.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import java.util.*;
import javax.annotation.*;
import javax.xml.bind.annotation.*;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@GraphQLName("GasCalculatedConsumption")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "GasCalculatedConsumption")
public class GasCalculatedConsumption {

	@Schema(description = "Wether it's used for water heating.")
	public Boolean getAcsUse() {
		return acsUse;
	}

	public void setAcsUse(Boolean acsUse) {
		this.acsUse = acsUse;
	}

	@JsonIgnore
	public void setAcsUse(
		UnsafeSupplier<Boolean, Exception> acsUseUnsafeSupplier) {

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
	protected Boolean acsUse;

	@Schema(description = "Kwh/year consumed")
	public Integer getElectricityConsumption() {
		return electricityConsumption;
	}

	public void setElectricityConsumption(Integer electricityConsumption) {
		this.electricityConsumption = electricityConsumption;
	}

	@JsonIgnore
	public void setElectricityConsumption(
		UnsafeSupplier<Integer, Exception>
			electricityConsumptionUnsafeSupplier) {

		try {
			electricityConsumption = electricityConsumptionUnsafeSupplier.get();
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
	protected Integer electricityConsumption;

	@Schema(description = "Select Butane, GLP, GOC or Electricity")
	public String getEnergyType() {
		return energyType;
	}

	public void setEnergyType(String energyType) {
		this.energyType = energyType;
	}

	@JsonIgnore
	public void setEnergyType(
		UnsafeSupplier<String, Exception> energyTypeUnsafeSupplier) {

		try {
			energyType = energyTypeUnsafeSupplier.get();
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
	protected String energyType;

	@Schema(description = "Wether it's used for house heating.")
	public Boolean getHeatingUse() {
		return heatingUse;
	}

	public void setHeatingUse(Boolean heatingUse) {
		this.heatingUse = heatingUse;
	}

	@JsonIgnore
	public void setHeatingUse(
		UnsafeSupplier<Boolean, Exception> heatingUseUnsafeSupplier) {

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
	protected Boolean heatingUse;

	@Schema(description = "Wether it's used for the kitchen.")
	public Boolean getKitchenUse() {
		return kitchenUse;
	}

	public void setKitchenUse(Boolean kitchenUse) {
		this.kitchenUse = kitchenUse;
	}

	@JsonIgnore
	public void setKitchenUse(
		UnsafeSupplier<Boolean, Exception> kitchenUseUnsafeSupplier) {

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
	protected Boolean kitchenUse;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GasCalculatedConsumption)) {
			return false;
		}

		GasCalculatedConsumption gasCalculatedConsumption =
			(GasCalculatedConsumption)object;

		return Objects.equals(toString(), gasCalculatedConsumption.toString());
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

			sb.append(acsUse);
		}

		if (electricityConsumption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricityConsumption\": ");

			sb.append(electricityConsumption);
		}

		if (energyType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"energyType\": ");

			sb.append("\"");

			sb.append(_escape(energyType));

			sb.append("\"");
		}

		if (heatingUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"heatingUse\": ");

			sb.append(heatingUse);
		}

		if (kitchenUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"kitchenUse\": ");

			sb.append(kitchenUse);
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