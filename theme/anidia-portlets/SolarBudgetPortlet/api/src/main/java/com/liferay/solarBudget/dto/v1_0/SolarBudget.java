package com.liferay.solarBudget.dto.v1_0;

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
@GraphQLName("SolarBudget")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "SolarBudget")
public class SolarBudget {

	@Schema(description = "Additional Installation")
	public String getAdditionalPanelsInstallation() {
		return additionalPanelsInstallation;
	}

	public void setAdditionalPanelsInstallation(
		String additionalPanelsInstallation) {

		this.additionalPanelsInstallation = additionalPanelsInstallation;
	}

	@JsonIgnore
	public void setAdditionalPanelsInstallation(
		UnsafeSupplier<String, Exception>
			additionalPanelsInstallationUnsafeSupplier) {

		try {
			additionalPanelsInstallation =
				additionalPanelsInstallationUnsafeSupplier.get();
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
	protected String additionalPanelsInstallation;

	@Schema(description = "Battery")
	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	@JsonIgnore
	public void setBattery(
		UnsafeSupplier<String, Exception> batteryUnsafeSupplier) {

		try {
			battery = batteryUnsafeSupplier.get();
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
	protected String battery;

	@Schema(description = "Car charger")
	public String getCarCharger() {
		return carCharger;
	}

	public void setCarCharger(String carCharger) {
		this.carCharger = carCharger;
	}

	@JsonIgnore
	public void setCarCharger(
		UnsafeSupplier<String, Exception> carChargerUnsafeSupplier) {

		try {
			carCharger = carChargerUnsafeSupplier.get();
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
	protected String carCharger;

	@Schema(description = "Inverter")
	@Valid
	public SolarOutputInverter getInverter() {
		return inverter;
	}

	public void setInverter(SolarOutputInverter inverter) {
		this.inverter = inverter;
	}

	@JsonIgnore
	public void setInverter(
		UnsafeSupplier<SolarOutputInverter, Exception> inverterUnsafeSupplier) {

		try {
			inverter = inverterUnsafeSupplier.get();
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
	protected SolarOutputInverter inverter;

	@Schema(description = "Extra inverter")
	public String getInverterExtra() {
		return inverterExtra;
	}

	public void setInverterExtra(String inverterExtra) {
		this.inverterExtra = inverterExtra;
	}

	@JsonIgnore
	public void setInverterExtra(
		UnsafeSupplier<String, Exception> inverterExtraUnsafeSupplier) {

		try {
			inverterExtra = inverterExtraUnsafeSupplier.get();
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
	protected String inverterExtra;

	@Schema(description = "Extra panels")
	public String getPanelsExtra() {
		return panelsExtra;
	}

	public void setPanelsExtra(String panelsExtra) {
		this.panelsExtra = panelsExtra;
	}

	@JsonIgnore
	public void setPanelsExtra(
		UnsafeSupplier<String, Exception> panelsExtraUnsafeSupplier) {

		try {
			panelsExtra = panelsExtraUnsafeSupplier.get();
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
	protected String panelsExtra;

	@Schema(description = "Tipo de paneles")
	public String getPanelsType() {
		return panelsType;
	}

	public void setPanelsType(String panelsType) {
		this.panelsType = panelsType;
	}

	@JsonIgnore
	public void setPanelsType(
		UnsafeSupplier<String, Exception> panelsTypeUnsafeSupplier) {

		try {
			panelsType = panelsTypeUnsafeSupplier.get();
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
	protected String panelsType;

	@Schema(description = "Extra pergola")
	public String getPergolaExtra() {
		return pergolaExtra;
	}

	public void setPergolaExtra(String pergolaExtra) {
		this.pergolaExtra = pergolaExtra;
	}

	@JsonIgnore
	public void setPergolaExtra(
		UnsafeSupplier<String, Exception> pergolaExtraUnsafeSupplier) {

		try {
			pergolaExtra = pergolaExtraUnsafeSupplier.get();
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
	protected String pergolaExtra;

	@Schema(description = "Extra pipeline")
	public String getPipelineExtra() {
		return pipelineExtra;
	}

	public void setPipelineExtra(String pipelineExtra) {
		this.pipelineExtra = pipelineExtra;
	}

	@JsonIgnore
	public void setPipelineExtra(
		UnsafeSupplier<String, Exception> pipelineExtraUnsafeSupplier) {

		try {
			pipelineExtra = pipelineExtraUnsafeSupplier.get();
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
	protected String pipelineExtra;

	@Schema(description = "Extra roof")
	public String getRoofExtra() {
		return roofExtra;
	}

	public void setRoofExtra(String roofExtra) {
		this.roofExtra = roofExtra;
	}

	@JsonIgnore
	public void setRoofExtra(
		UnsafeSupplier<String, Exception> roofExtraUnsafeSupplier) {

		try {
			roofExtra = roofExtraUnsafeSupplier.get();
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
	protected String roofExtra;

	@Schema(description = "Size")
	@Valid
	public SolarBudgetSize getSize() {
		return size;
	}

	public void setSize(SolarBudgetSize size) {
		this.size = size;
	}

	@JsonIgnore
	public void setSize(
		UnsafeSupplier<SolarBudgetSize, Exception> sizeUnsafeSupplier) {

		try {
			size = sizeUnsafeSupplier.get();
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
	protected SolarBudgetSize size;

	@Schema(description = "Superior installation data")
	@Valid
	public SuperiorInstallation getSuperiorInstallation() {
		return superiorInstallation;
	}

	public void setSuperiorInstallation(
		SuperiorInstallation superiorInstallation) {

		this.superiorInstallation = superiorInstallation;
	}

	@JsonIgnore
	public void setSuperiorInstallation(
		UnsafeSupplier<SuperiorInstallation, Exception>
			superiorInstallationUnsafeSupplier) {

		try {
			superiorInstallation = superiorInstallationUnsafeSupplier.get();
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
	protected SuperiorInstallation superiorInstallation;

	@Schema(description = "Total power of the budget (KwP)")
	public String getTotalPowerInstalled() {
		return totalPowerInstalled;
	}

	public void setTotalPowerInstalled(String totalPowerInstalled) {
		this.totalPowerInstalled = totalPowerInstalled;
	}

	@JsonIgnore
	public void setTotalPowerInstalled(
		UnsafeSupplier<String, Exception> totalPowerInstalledUnsafeSupplier) {

		try {
			totalPowerInstalled = totalPowerInstalledUnsafeSupplier.get();
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
	protected String totalPowerInstalled;

	@Schema(description = "Total price")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@JsonIgnore
	public void setTotalPrice(
		UnsafeSupplier<String, Exception> totalPriceUnsafeSupplier) {

		try {
			totalPrice = totalPriceUnsafeSupplier.get();
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
	protected String totalPrice;

	@Schema(description = "Extra triphasic")
	public String getTriphasicExtra() {
		return triphasicExtra;
	}

	public void setTriphasicExtra(String triphasicExtra) {
		this.triphasicExtra = triphasicExtra;
	}

	@JsonIgnore
	public void setTriphasicExtra(
		UnsafeSupplier<String, Exception> triphasicExtraUnsafeSupplier) {

		try {
			triphasicExtra = triphasicExtraUnsafeSupplier.get();
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
	protected String triphasicExtra;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SolarBudget)) {
			return false;
		}

		SolarBudget solarBudget = (SolarBudget)object;

		return Objects.equals(toString(), solarBudget.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (additionalPanelsInstallation != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"additionalPanelsInstallation\": ");

			sb.append("\"");

			sb.append(_escape(additionalPanelsInstallation));

			sb.append("\"");
		}

		if (battery != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"battery\": ");

			sb.append("\"");

			sb.append(_escape(battery));

			sb.append("\"");
		}

		if (carCharger != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"carCharger\": ");

			sb.append("\"");

			sb.append(_escape(carCharger));

			sb.append("\"");
		}

		if (inverter != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inverter\": ");

			sb.append(String.valueOf(inverter));
		}

		if (inverterExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inverterExtra\": ");

			sb.append("\"");

			sb.append(_escape(inverterExtra));

			sb.append("\"");
		}

		if (panelsExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelsExtra\": ");

			sb.append("\"");

			sb.append(_escape(panelsExtra));

			sb.append("\"");
		}

		if (panelsType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelsType\": ");

			sb.append("\"");

			sb.append(_escape(panelsType));

			sb.append("\"");
		}

		if (pergolaExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pergolaExtra\": ");

			sb.append("\"");

			sb.append(_escape(pergolaExtra));

			sb.append("\"");
		}

		if (pipelineExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pipelineExtra\": ");

			sb.append("\"");

			sb.append(_escape(pipelineExtra));

			sb.append("\"");
		}

		if (roofExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roofExtra\": ");

			sb.append("\"");

			sb.append(_escape(roofExtra));

			sb.append("\"");
		}

		if (size != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"size\": ");

			sb.append(String.valueOf(size));
		}

		if (superiorInstallation != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"superiorInstallation\": ");

			sb.append(String.valueOf(superiorInstallation));
		}

		if (totalPowerInstalled != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalPowerInstalled\": ");

			sb.append("\"");

			sb.append(_escape(totalPowerInstalled));

			sb.append("\"");
		}

		if (totalPrice != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalPrice\": ");

			sb.append("\"");

			sb.append(_escape(totalPrice));

			sb.append("\"");
		}

		if (triphasicExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"triphasicExtra\": ");

			sb.append("\"");

			sb.append(_escape(triphasicExtra));

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