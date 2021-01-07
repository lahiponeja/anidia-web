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

	@GraphQLName("AdditionalPanels")
	public static enum AdditionalPanels {

		SI("Si"), NO("No");

		@JsonCreator
		public static AdditionalPanels create(String value) {
			for (AdditionalPanels additionalPanels : values()) {
				if (Objects.equals(additionalPanels.getValue(), value)) {
					return additionalPanels;
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

		private AdditionalPanels(String value) {
			_value = value;
		}

		private final String _value;

	}

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

	@GraphQLName("InverterType")
	public static enum InverterType {

		STANDARD("Standard"), FRONIUS("Fronius");

		@JsonCreator
		public static InverterType create(String value) {
			for (InverterType inverterType : values()) {
				if (Objects.equals(inverterType.getValue(), value)) {
					return inverterType;
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

		private InverterType(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("PanelsSelected")
	public static enum PanelsSelected {

		STANDARD("Standard"), DISEO_LG("Diseño (LG)");

		@JsonCreator
		public static PanelsSelected create(String value) {
			for (PanelsSelected panelsSelected : values()) {
				if (Objects.equals(panelsSelected.getValue(), value)) {
					return panelsSelected;
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

		private PanelsSelected(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("RoofType")
	public static enum RoofType {

		PLANO_O_INCLINACIN_MENOR_A_20("Plano o inclinación menor a 20º"),
		INCLINACIN_SUPERIOR_A_20("Inclinación superior a 20º");

		@JsonCreator
		public static RoofType create(String value) {
			for (RoofType roofType : values()) {
				if (Objects.equals(roofType.getValue(), value)) {
					return roofType;
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

		private RoofType(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Schema(description = "User wants aditional panels")
	@Valid
	public AdditionalPanels getAdditionalPanels() {
		return additionalPanels;
	}

	@JsonIgnore
	public String getAdditionalPanelsAsString() {
		if (additionalPanels == null) {
			return null;
		}

		return additionalPanels.toString();
	}

	public void setAdditionalPanels(AdditionalPanels additionalPanels) {
		this.additionalPanels = additionalPanels;
	}

	@JsonIgnore
	public void setAdditionalPanels(
		UnsafeSupplier<AdditionalPanels, Exception>
			additionalPanelsUnsafeSupplier) {

		try {
			additionalPanels = additionalPanelsUnsafeSupplier.get();
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
	protected AdditionalPanels additionalPanels;

	@Schema(description = "Annual Consumption")
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

	@Schema
	@Valid
	public ElectricalAppliance getElectricalAppliances() {
		return electricalAppliances;
	}

	public void setElectricalAppliances(
		ElectricalAppliance electricalAppliances) {

		this.electricalAppliances = electricalAppliances;
	}

	@JsonIgnore
	public void setElectricalAppliances(
		UnsafeSupplier<ElectricalAppliance, Exception>
			electricalAppliancesUnsafeSupplier) {

		try {
			electricalAppliances = electricalAppliancesUnsafeSupplier.get();
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
	protected ElectricalAppliance electricalAppliances;

	@Schema(description = "Entry key")
	public String getEntryKey() {
		return entryKey;
	}

	public void setEntryKey(String entryKey) {
		this.entryKey = entryKey;
	}

	@JsonIgnore
	public void setEntryKey(
		UnsafeSupplier<String, Exception> entryKeyUnsafeSupplier) {

		try {
			entryKey = entryKeyUnsafeSupplier.get();
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
	protected String entryKey;

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

	@Schema(description = "Inverter type")
	@Valid
	public InverterType getInverterType() {
		return inverterType;
	}

	@JsonIgnore
	public String getInverterTypeAsString() {
		if (inverterType == null) {
			return null;
		}

		return inverterType.toString();
	}

	public void setInverterType(InverterType inverterType) {
		this.inverterType = inverterType;
	}

	@JsonIgnore
	public void setInverterType(
		UnsafeSupplier<InverterType, Exception> inverterTypeUnsafeSupplier) {

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
	protected InverterType inverterType;

	@Schema(description = "Monthly consumprion")
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

	@Schema(description = "Number of additional panels")
	public Integer getNumberAdditionalPanels() {
		return numberAdditionalPanels;
	}

	public void setNumberAdditionalPanels(Integer numberAdditionalPanels) {
		this.numberAdditionalPanels = numberAdditionalPanels;
	}

	@JsonIgnore
	public void setNumberAdditionalPanels(
		UnsafeSupplier<Integer, Exception>
			numberAdditionalPanelsUnsafeSupplier) {

		try {
			numberAdditionalPanels = numberAdditionalPanelsUnsafeSupplier.get();
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
	protected Integer numberAdditionalPanels;

	@Schema(description = "Selected panels")
	@Valid
	public PanelsSelected getPanelsSelected() {
		return panelsSelected;
	}

	@JsonIgnore
	public String getPanelsSelectedAsString() {
		if (panelsSelected == null) {
			return null;
		}

		return panelsSelected.toString();
	}

	public void setPanelsSelected(PanelsSelected panelsSelected) {
		this.panelsSelected = panelsSelected;
	}

	@JsonIgnore
	public void setPanelsSelected(
		UnsafeSupplier<PanelsSelected, Exception>
			panelsSelectedUnsafeSupplier) {

		try {
			panelsSelected = panelsSelectedUnsafeSupplier.get();
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
	protected PanelsSelected panelsSelected;

	@Schema(description = "Pipeline meters")
	public Integer getPipelineMeters() {
		return pipelineMeters;
	}

	public void setPipelineMeters(Integer pipelineMeters) {
		this.pipelineMeters = pipelineMeters;
	}

	@JsonIgnore
	public void setPipelineMeters(
		UnsafeSupplier<Integer, Exception> pipelineMetersUnsafeSupplier) {

		try {
			pipelineMeters = pipelineMetersUnsafeSupplier.get();
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
	protected Integer pipelineMeters;

	@Schema(description = "Roof type")
	@Valid
	public RoofType getRoofType() {
		return roofType;
	}

	@JsonIgnore
	public String getRoofTypeAsString() {
		if (roofType == null) {
			return null;
		}

		return roofType.toString();
	}

	public void setRoofType(RoofType roofType) {
		this.roofType = roofType;
	}

	@JsonIgnore
	public void setRoofType(
		UnsafeSupplier<RoofType, Exception> roofTypeUnsafeSupplier) {

		try {
			roofType = roofTypeUnsafeSupplier.get();
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
	protected RoofType roofType;

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

		if (additionalPanels != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"additionalPanels\": ");

			sb.append("\"");

			sb.append(additionalPanels);

			sb.append("\"");
		}

		if (annualConsumption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"annualConsumption\": ");

			sb.append(annualConsumption);
		}

		if (electricalAppliances != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricalAppliances\": ");

			sb.append(String.valueOf(electricalAppliances));
		}

		if (entryKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entryKey\": ");

			sb.append("\"");

			sb.append(_escape(entryKey));

			sb.append("\"");
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

		if (inverterType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inverterType\": ");

			sb.append("\"");

			sb.append(inverterType);

			sb.append("\"");
		}

		if (monthlyConsumption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"monthlyConsumption\": ");

			sb.append(monthlyConsumption);
		}

		if (numberAdditionalPanels != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"numberAdditionalPanels\": ");

			sb.append(numberAdditionalPanels);
		}

		if (panelsSelected != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"panelsSelected\": ");

			sb.append("\"");

			sb.append(panelsSelected);

			sb.append("\"");
		}

		if (pipelineMeters != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pipelineMeters\": ");

			sb.append(pipelineMeters);
		}

		if (roofType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roofType\": ");

			sb.append("\"");

			sb.append(roofType);

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