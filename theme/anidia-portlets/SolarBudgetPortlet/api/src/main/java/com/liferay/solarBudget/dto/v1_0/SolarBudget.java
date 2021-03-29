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
	@Valid
	public BudgetExtra getAdditionalPanelsInstallation() {
		return additionalPanelsInstallation;
	}

	public void setAdditionalPanelsInstallation(
		BudgetExtra additionalPanelsInstallation) {

		this.additionalPanelsInstallation = additionalPanelsInstallation;
	}

	@JsonIgnore
	public void setAdditionalPanelsInstallation(
		UnsafeSupplier<BudgetExtra, Exception>
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
	protected BudgetExtra additionalPanelsInstallation;

	@Schema(description = "Battery")
	@Valid
	public BudgetExtra getBattery() {
		return battery;
	}

	public void setBattery(BudgetExtra battery) {
		this.battery = battery;
	}

	@JsonIgnore
	public void setBattery(
		UnsafeSupplier<BudgetExtra, Exception> batteryUnsafeSupplier) {

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
	protected BudgetExtra battery;

	@Schema(description = "Car charger")
	@Valid
	public BudgetExtra getCarCharger() {
		return carCharger;
	}

	public void setCarCharger(BudgetExtra carCharger) {
		this.carCharger = carCharger;
	}

	@JsonIgnore
	public void setCarCharger(
		UnsafeSupplier<BudgetExtra, Exception> carChargerUnsafeSupplier) {

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
	protected BudgetExtra carCharger;

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
	@Valid
	public BudgetExtra getInverterExtra() {
		return inverterExtra;
	}

	public void setInverterExtra(BudgetExtra inverterExtra) {
		this.inverterExtra = inverterExtra;
	}

	@JsonIgnore
	public void setInverterExtra(
		UnsafeSupplier<BudgetExtra, Exception> inverterExtraUnsafeSupplier) {

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
	protected BudgetExtra inverterExtra;

	@Schema(description = "Extra panels")
	@Valid
	public BudgetExtra getPanelsExtra() {
		return panelsExtra;
	}

	public void setPanelsExtra(BudgetExtra panelsExtra) {
		this.panelsExtra = panelsExtra;
	}

	@JsonIgnore
	public void setPanelsExtra(
		UnsafeSupplier<BudgetExtra, Exception> panelsExtraUnsafeSupplier) {

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
	protected BudgetExtra panelsExtra;

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
	@Valid
	public BudgetExtra getPergolaExtra() {
		return pergolaExtra;
	}

	public void setPergolaExtra(BudgetExtra pergolaExtra) {
		this.pergolaExtra = pergolaExtra;
	}

	@JsonIgnore
	public void setPergolaExtra(
		UnsafeSupplier<BudgetExtra, Exception> pergolaExtraUnsafeSupplier) {

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
	protected BudgetExtra pergolaExtra;

	@Schema(description = "Extra pipeline")
	@Valid
	public BudgetExtra getPipelineExtra() {
		return pipelineExtra;
	}

	public void setPipelineExtra(BudgetExtra pipelineExtra) {
		this.pipelineExtra = pipelineExtra;
	}

	@JsonIgnore
	public void setPipelineExtra(
		UnsafeSupplier<BudgetExtra, Exception> pipelineExtraUnsafeSupplier) {

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
	protected BudgetExtra pipelineExtra;

	@Schema(description = "Extra roof")
	@Valid
	public BudgetExtra getRoofExtra() {
		return roofExtra;
	}

	public void setRoofExtra(BudgetExtra roofExtra) {
		this.roofExtra = roofExtra;
	}

	@JsonIgnore
	public void setRoofExtra(
		UnsafeSupplier<BudgetExtra, Exception> roofExtraUnsafeSupplier) {

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
	protected BudgetExtra roofExtra;

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

	@Schema(description = "Price for the superior inverter")
	@Valid
	public BudgetExtra getSuperiorInverterExtra() {
		return superiorInverterExtra;
	}

	public void setSuperiorInverterExtra(BudgetExtra superiorInverterExtra) {
		this.superiorInverterExtra = superiorInverterExtra;
	}

	@JsonIgnore
	public void setSuperiorInverterExtra(
		UnsafeSupplier<BudgetExtra, Exception>
			superiorInverterExtraUnsafeSupplier) {

		try {
			superiorInverterExtra = superiorInverterExtraUnsafeSupplier.get();
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
	protected BudgetExtra superiorInverterExtra;

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
	@Valid
	public BudgetExtra getTriphasicExtra() {
		return triphasicExtra;
	}

	public void setTriphasicExtra(BudgetExtra triphasicExtra) {
		this.triphasicExtra = triphasicExtra;
	}

	@JsonIgnore
	public void setTriphasicExtra(
		UnsafeSupplier<BudgetExtra, Exception> triphasicExtraUnsafeSupplier) {

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
	protected BudgetExtra triphasicExtra;

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

			sb.append(String.valueOf(additionalPanelsInstallation));
		}

		if (battery != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"battery\": ");

			sb.append(String.valueOf(battery));
		}

		if (carCharger != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"carCharger\": ");

			sb.append(String.valueOf(carCharger));
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

			sb.append(String.valueOf(inverterExtra));
		}

		if (panelsExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelsExtra\": ");

			sb.append(String.valueOf(panelsExtra));
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

			sb.append(String.valueOf(pergolaExtra));
		}

		if (pipelineExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pipelineExtra\": ");

			sb.append(String.valueOf(pipelineExtra));
		}

		if (roofExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roofExtra\": ");

			sb.append(String.valueOf(roofExtra));
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

		if (superiorInverterExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"superiorInverterExtra\": ");

			sb.append(String.valueOf(superiorInverterExtra));
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

			sb.append(String.valueOf(triphasicExtra));
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