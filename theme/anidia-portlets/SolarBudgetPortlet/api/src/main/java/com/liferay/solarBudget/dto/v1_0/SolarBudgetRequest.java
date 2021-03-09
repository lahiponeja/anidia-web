package com.liferay.solarBudget.dto.v1_0;

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
@GraphQLName("SolarBudgetRequest")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "SolarBudgetRequest")
public class SolarBudgetRequest {

	@GraphQLName("HouseType")
	public static enum HouseType {

		UNIFAMILIAR_DE_1_PLANTA_SIN_A_A("Unifamiliar de 1 planta sin A/A"),
		UNIFAMILIAR_DE_1_PLANTA_CON_A_A("Unifamiliar de 1 planta con A/A"),
		UNIFAMILIAR_DE_2_3_PLANTAS_SIN_A_A_NI_PISCINA(
			"Unifamiliar de 2-3 plantas sin A/A ni piscina"),
		UNIFAMILIAR_DE_2_3_PLANTAS_CON_A_A_O_PISCINA(
			"Unifamiliar de 2-3 plantas con A/A o piscina"),
		UNIFAMILIAR_DE_2_3_PLANTAS_CON_A_A_Y_PISCINA_O_CALEFACCIN_ELCTRICA(
			"Unifamiliar de 2-3 plantas con A/A y piscina o calefacción eléctrica");

		@JsonCreator
		public static HouseType create(String value) {
			for (HouseType houseType : values()) {
				if (Objects.equals(houseType.getValue(), value)) {
					return houseType;
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

		private HouseType(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("PanelsType")
	public static enum PanelsType {

		STANDARD("standard"), LG("lg");

		@JsonCreator
		public static PanelsType create(String value) {
			for (PanelsType panelsType : values()) {
				if (Objects.equals(panelsType.getValue(), value)) {
					return panelsType;
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

		private PanelsType(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Schema(description = "Yearly consumption in KW")
	public Integer getAnnualConsumption() {
		return annualConsumption;
	}

	public void setAnnualConsumption(Integer annualConsumption) {
		this.annualConsumption = annualConsumption;
	}

	@JsonIgnore
	public void setAnnualConsumption(
		UnsafeSupplier<Integer, Exception> annualConsumptionUnsafeSupplier) {

		try {
			annualConsumption = annualConsumptionUnsafeSupplier.get();
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
	protected Integer annualConsumption;

	@Schema(description = "Kind of house")
	@Valid
	public HouseType getHouseType() {
		return houseType;
	}

	@JsonIgnore
	public String getHouseTypeAsString() {
		if (houseType == null) {
			return null;
		}

		return houseType.toString();
	}

	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}

	@JsonIgnore
	public void setHouseType(
		UnsafeSupplier<HouseType, Exception> houseTypeUnsafeSupplier) {

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
	protected HouseType houseType;

	@Schema(description = "Monthly consumption in euros")
	public Integer getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(Integer monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	@JsonIgnore
	public void setMonthlyConsumption(
		UnsafeSupplier<Integer, Exception> monthlyConsumptionUnsafeSupplier) {

		try {
			monthlyConsumption = monthlyConsumptionUnsafeSupplier.get();
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
	protected Integer monthlyConsumption;

	@Schema(description = "Kind of panels to be included in the budget")
	@Valid
	public PanelsType getPanelsType() {
		return panelsType;
	}

	@JsonIgnore
	public String getPanelsTypeAsString() {
		if (panelsType == null) {
			return null;
		}

		return panelsType.toString();
	}

	public void setPanelsType(PanelsType panelsType) {
		this.panelsType = panelsType;
	}

	@JsonIgnore
	public void setPanelsType(
		UnsafeSupplier<PanelsType, Exception> panelsTypeUnsafeSupplier) {

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
	protected PanelsType panelsType;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SolarBudgetRequest)) {
			return false;
		}

		SolarBudgetRequest solarBudgetRequest = (SolarBudgetRequest)object;

		return Objects.equals(toString(), solarBudgetRequest.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (annualConsumption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"annualConsumption\": ");

			sb.append(annualConsumption);
		}

		if (houseType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"houseType\": ");

			sb.append("\"");

			sb.append(houseType);

			sb.append("\"");
		}

		if (monthlyConsumption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"monthlyConsumption\": ");

			sb.append(monthlyConsumption);
		}

		if (panelsType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelsType\": ");

			sb.append("\"");

			sb.append(panelsType);

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