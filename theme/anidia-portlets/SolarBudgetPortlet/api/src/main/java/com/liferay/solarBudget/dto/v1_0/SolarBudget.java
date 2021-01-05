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
	public SolarBudgetExtra getAdditionalPanelsInstallation() {
		return additionalPanelsInstallation;
	}

	public void setAdditionalPanelsInstallation(
		SolarBudgetExtra additionalPanelsInstallation) {

		this.additionalPanelsInstallation = additionalPanelsInstallation;
	}

	@JsonIgnore
	public void setAdditionalPanelsInstallation(
		UnsafeSupplier<SolarBudgetExtra, Exception>
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
	protected SolarBudgetExtra additionalPanelsInstallation;

	@Schema(description = "Battery")
	@Valid
	public SolarBudgetExtra getBattery() {
		return battery;
	}

	public void setBattery(SolarBudgetExtra battery) {
		this.battery = battery;
	}

	@JsonIgnore
	public void setBattery(
		UnsafeSupplier<SolarBudgetExtra, Exception> batteryUnsafeSupplier) {

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
	protected SolarBudgetExtra battery;

	@Schema(description = "Car charger")
	@Valid
	public SolarBudgetExtra getCarCharger() {
		return carCharger;
	}

	public void setCarCharger(SolarBudgetExtra carCharger) {
		this.carCharger = carCharger;
	}

	@JsonIgnore
	public void setCarCharger(
		UnsafeSupplier<SolarBudgetExtra, Exception> carChargerUnsafeSupplier) {

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
	protected SolarBudgetExtra carCharger;

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
	public SolarBudgetExtra getInverterExtra() {
		return inverterExtra;
	}

	public void setInverterExtra(SolarBudgetExtra inverterExtra) {
		this.inverterExtra = inverterExtra;
	}

	@JsonIgnore
	public void setInverterExtra(
		UnsafeSupplier<SolarBudgetExtra, Exception>
			inverterExtraUnsafeSupplier) {

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
	protected SolarBudgetExtra inverterExtra;

	@Schema(description = "Extra panels")
	@Valid
	public SolarBudgetExtra getPanelsExtra() {
		return panelsExtra;
	}

	public void setPanelsExtra(SolarBudgetExtra panelsExtra) {
		this.panelsExtra = panelsExtra;
	}

	@JsonIgnore
	public void setPanelsExtra(
		UnsafeSupplier<SolarBudgetExtra, Exception> panelsExtraUnsafeSupplier) {

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
	protected SolarBudgetExtra panelsExtra;

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
	public SolarBudgetExtra getPergolaExtra() {
		return pergolaExtra;
	}

	public void setPergolaExtra(SolarBudgetExtra pergolaExtra) {
		this.pergolaExtra = pergolaExtra;
	}

	@JsonIgnore
	public void setPergolaExtra(
		UnsafeSupplier<SolarBudgetExtra, Exception>
			pergolaExtraUnsafeSupplier) {

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
	protected SolarBudgetExtra pergolaExtra;

	@Schema(description = "Extra pipeline")
	@Valid
	public SolarBudgetExtra getPipelineExtra() {
		return pipelineExtra;
	}

	public void setPipelineExtra(SolarBudgetExtra pipelineExtra) {
		this.pipelineExtra = pipelineExtra;
	}

	@JsonIgnore
	public void setPipelineExtra(
		UnsafeSupplier<SolarBudgetExtra, Exception>
			pipelineExtraUnsafeSupplier) {

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
	protected SolarBudgetExtra pipelineExtra;

	@Schema(description = "Extra roof")
	@Valid
	public SolarBudgetExtra getRoofExtra() {
		return roofExtra;
	}

	public void setRoofExtra(SolarBudgetExtra roofExtra) {
		this.roofExtra = roofExtra;
	}

	@JsonIgnore
	public void setRoofExtra(
		UnsafeSupplier<SolarBudgetExtra, Exception> roofExtraUnsafeSupplier) {

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
	protected SolarBudgetExtra roofExtra;

	@Schema(description = "Size")
	@Valid
	public SolarBudgetExtra getSize() {
		return size;
	}

	public void setSize(SolarBudgetExtra size) {
		this.size = size;
	}

	@JsonIgnore
	public void setSize(
		UnsafeSupplier<SolarBudgetExtra, Exception> sizeUnsafeSupplier) {

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
	protected SolarBudgetExtra size;

	@Schema(description = "Superior installation data")
	@Valid
	public SolarSuperiorInstallation getSuperiorInstallation() {
		return superiorInstallation;
	}

	public void setSuperiorInstallation(
		SolarSuperiorInstallation superiorInstallation) {

		this.superiorInstallation = superiorInstallation;
	}

	@JsonIgnore
	public void setSuperiorInstallation(
		UnsafeSupplier<SolarSuperiorInstallation, Exception>
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
	protected SolarSuperiorInstallation superiorInstallation;

	@Schema(description = "Total prize")
	public String getTotalPrize() {
		return totalPrize;
	}

	public void setTotalPrize(String totalPrize) {
		this.totalPrize = totalPrize;
	}

	@JsonIgnore
	public void setTotalPrize(
		UnsafeSupplier<String, Exception> totalPrizeUnsafeSupplier) {

		try {
			totalPrize = totalPrizeUnsafeSupplier.get();
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
	protected String totalPrize;

	@Schema(description = "Extra triphasic")
	@Valid
	public SolarBudgetExtra getTriphasicExtra() {
		return triphasicExtra;
	}

	public void setTriphasicExtra(SolarBudgetExtra triphasicExtra) {
		this.triphasicExtra = triphasicExtra;
	}

	@JsonIgnore
	public void setTriphasicExtra(
		UnsafeSupplier<SolarBudgetExtra, Exception>
			triphasicExtraUnsafeSupplier) {

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
	protected SolarBudgetExtra triphasicExtra;

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

		if (totalPrize != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalPrize\": ");

			sb.append("\"");

			sb.append(_escape(totalPrize));

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