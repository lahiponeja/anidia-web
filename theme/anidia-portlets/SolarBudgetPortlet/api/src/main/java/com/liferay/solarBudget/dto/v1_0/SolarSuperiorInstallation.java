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
@GraphQLName("SolarSuperiorInstallation")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "SolarSuperiorInstallation")
public class SolarSuperiorInstallation {

	@Schema
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

	@Schema
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

	@Schema
	@Valid
	public SolarBudgetExtra getCarCharcher() {
		return carCharcher;
	}

	public void setCarCharcher(SolarBudgetExtra carCharcher) {
		this.carCharcher = carCharcher;
	}

	@JsonIgnore
	public void setCarCharcher(
		UnsafeSupplier<SolarBudgetExtra, Exception> carCharcherUnsafeSupplier) {

		try {
			carCharcher = carCharcherUnsafeSupplier.get();
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
	protected SolarBudgetExtra carCharcher;

	@Schema
	public String getExtraFornius() {
		return extraFornius;
	}

	public void setExtraFornius(String extraFornius) {
		this.extraFornius = extraFornius;
	}

	@JsonIgnore
	public void setExtraFornius(
		UnsafeSupplier<String, Exception> extraForniusUnsafeSupplier) {

		try {
			extraFornius = extraForniusUnsafeSupplier.get();
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
	protected String extraFornius;

	@Schema
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

	@Schema
	public String getInverterType() {
		return inverterType;
	}

	public void setInverterType(String inverterType) {
		this.inverterType = inverterType;
	}

	@JsonIgnore
	public void setInverterType(
		UnsafeSupplier<String, Exception> inverterTypeUnsafeSupplier) {

		try {
			inverterType = inverterTypeUnsafeSupplier.get();
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
	protected String inverterType;

	@Schema
	public String getPanelType() {
		return panelType;
	}

	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}

	@JsonIgnore
	public void setPanelType(
		UnsafeSupplier<String, Exception> panelTypeUnsafeSupplier) {

		try {
			panelType = panelTypeUnsafeSupplier.get();
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
	protected String panelType;

	@Schema
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

	@Schema
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

	@Schema
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

	@Schema
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

	@Schema
	@Valid
	public SuperiorSize getSuperiorSize() {
		return superiorSize;
	}

	public void setSuperiorSize(SuperiorSize superiorSize) {
		this.superiorSize = superiorSize;
	}

	@JsonIgnore
	public void setSuperiorSize(
		UnsafeSupplier<SuperiorSize, Exception> superiorSizeUnsafeSupplier) {

		try {
			superiorSize = superiorSizeUnsafeSupplier.get();
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
	protected SuperiorSize superiorSize;

	@Schema
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

		if (!(object instanceof SolarSuperiorInstallation)) {
			return false;
		}

		SolarSuperiorInstallation solarSuperiorInstallation =
			(SolarSuperiorInstallation)object;

		return Objects.equals(toString(), solarSuperiorInstallation.toString());
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

		if (carCharcher != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"carCharcher\": ");

			sb.append(String.valueOf(carCharcher));
		}

		if (extraFornius != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"extraFornius\": ");

			sb.append("\"");

			sb.append(_escape(extraFornius));

			sb.append("\"");
		}

		if (inverterExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inverterExtra\": ");

			sb.append(String.valueOf(inverterExtra));
		}

		if (inverterType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inverterType\": ");

			sb.append("\"");

			sb.append(_escape(inverterType));

			sb.append("\"");
		}

		if (panelType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelType\": ");

			sb.append("\"");

			sb.append(_escape(panelType));

			sb.append("\"");
		}

		if (panelsExtra != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelsExtra\": ");

			sb.append(String.valueOf(panelsExtra));
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

		if (superiorSize != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"superiorSize\": ");

			sb.append(String.valueOf(superiorSize));
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