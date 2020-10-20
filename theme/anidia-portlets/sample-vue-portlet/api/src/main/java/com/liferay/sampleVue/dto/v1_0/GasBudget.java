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
@GraphQLName("GasBudget")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "GasBudget")
public class GasBudget {

	@Schema(description = "Base price of the budget")
	public String getBaseBudget() {
		return baseBudget;
	}

	public void setBaseBudget(String baseBudget) {
		this.baseBudget = baseBudget;
	}

	@JsonIgnore
	public void setBaseBudget(
		UnsafeSupplier<String, Exception> baseBudgetUnsafeSupplier) {

		try {
			baseBudget = baseBudgetUnsafeSupplier.get();
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
	protected String baseBudget;

	@Schema(description = "Discount")
	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	@JsonIgnore
	public void setBonus(
		UnsafeSupplier<String, Exception> bonusUnsafeSupplier) {

		try {
			bonus = bonusUnsafeSupplier.get();
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
	protected String bonus;

	@Schema(description = "Has the client a connect device in the kitchen?")
	@Valid
	public GasBudgetExtra getConnectDeviceToKitchen() {
		return connectDeviceToKitchen;
	}

	public void setConnectDeviceToKitchen(
		GasBudgetExtra connectDeviceToKitchen) {

		this.connectDeviceToKitchen = connectDeviceToKitchen;
	}

	@JsonIgnore
	public void setConnectDeviceToKitchen(
		UnsafeSupplier<GasBudgetExtra, Exception>
			connectDeviceToKitchenUnsafeSupplier) {

		try {
			connectDeviceToKitchen = connectDeviceToKitchenUnsafeSupplier.get();
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
	protected GasBudgetExtra connectDeviceToKitchen;

	@Schema(
		description = "Has the client controlled heating in the whole floor?"
	)
	@Valid
	public GasBudgetExtra getControllHeatingFloor() {
		return controllHeatingFloor;
	}

	public void setControllHeatingFloor(GasBudgetExtra controllHeatingFloor) {
		this.controllHeatingFloor = controllHeatingFloor;
	}

	@JsonIgnore
	public void setControllHeatingFloor(
		UnsafeSupplier<GasBudgetExtra, Exception>
			controllHeatingFloorUnsafeSupplier) {

		try {
			controllHeatingFloor = controllHeatingFloorUnsafeSupplier.get();
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
	protected GasBudgetExtra controllHeatingFloor;

	@Schema(description = "Has the client a convert device in the kitchen?")
	@Valid
	public GasBudgetExtra getConvertDeviceKitchen() {
		return convertDeviceKitchen;
	}

	public void setConvertDeviceKitchen(GasBudgetExtra convertDeviceKitchen) {
		this.convertDeviceKitchen = convertDeviceKitchen;
	}

	@JsonIgnore
	public void setConvertDeviceKitchen(
		UnsafeSupplier<GasBudgetExtra, Exception>
			convertDeviceKitchenUnsafeSupplier) {

		try {
			convertDeviceKitchen = convertDeviceKitchenUnsafeSupplier.get();
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
	protected GasBudgetExtra convertDeviceKitchen;

	@Schema(description = "Equipment included in the budget")
	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	@JsonIgnore
	public void setEquipment(
		UnsafeSupplier<String, Exception> equipmentUnsafeSupplier) {

		try {
			equipment = equipmentUnsafeSupplier.get();
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
	protected String equipment;

	@Schema(description = "Has the client a ventilation grill?")
	@Valid
	public GasBudgetExtra getHasVentilationGrill() {
		return hasVentilationGrill;
	}

	public void setHasVentilationGrill(GasBudgetExtra hasVentilationGrill) {
		this.hasVentilationGrill = hasVentilationGrill;
	}

	@JsonIgnore
	public void setHasVentilationGrill(
		UnsafeSupplier<GasBudgetExtra, Exception>
			hasVentilationGrillUnsafeSupplier) {

		try {
			hasVentilationGrill = hasVentilationGrillUnsafeSupplier.get();
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
	protected GasBudgetExtra hasVentilationGrill;

	@Schema(description = "Distance between the boiler and the window")
	@Valid
	public GasBudgetExtra getMetersBoilerToWindow() {
		return metersBoilerToWindow;
	}

	public void setMetersBoilerToWindow(GasBudgetExtra metersBoilerToWindow) {
		this.metersBoilerToWindow = metersBoilerToWindow;
	}

	@JsonIgnore
	public void setMetersBoilerToWindow(
		UnsafeSupplier<GasBudgetExtra, Exception>
			metersBoilerToWindowUnsafeSupplier) {

		try {
			metersBoilerToWindow = metersBoilerToWindowUnsafeSupplier.get();
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
	protected GasBudgetExtra metersBoilerToWindow;

	@Schema(description = "Distance to the water intake")
	@Valid
	public GasBudgetExtra getMetersWaterIntake() {
		return metersWaterIntake;
	}

	public void setMetersWaterIntake(GasBudgetExtra metersWaterIntake) {
		this.metersWaterIntake = metersWaterIntake;
	}

	@JsonIgnore
	public void setMetersWaterIntake(
		UnsafeSupplier<GasBudgetExtra, Exception>
			metersWaterIntakeUnsafeSupplier) {

		try {
			metersWaterIntake = metersWaterIntakeUnsafeSupplier.get();
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
	protected GasBudgetExtra metersWaterIntake;

	@Schema(description = "Title of the pack")
	public String getProposedPack() {
		return proposedPack;
	}

	public void setProposedPack(String proposedPack) {
		this.proposedPack = proposedPack;
	}

	@JsonIgnore
	public void setProposedPack(
		UnsafeSupplier<String, Exception> proposedPackUnsafeSupplier) {

		try {
			proposedPack = proposedPackUnsafeSupplier.get();
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
	protected String proposedPack;

	@Schema(description = "Number of the radiators in the bathroom.")
	@Valid
	public GasBudgetExtra getRadiatorsBathroom() {
		return radiatorsBathroom;
	}

	public void setRadiatorsBathroom(GasBudgetExtra radiatorsBathroom) {
		this.radiatorsBathroom = radiatorsBathroom;
	}

	@JsonIgnore
	public void setRadiatorsBathroom(
		UnsafeSupplier<GasBudgetExtra, Exception>
			radiatorsBathroomUnsafeSupplier) {

		try {
			radiatorsBathroom = radiatorsBathroomUnsafeSupplier.get();
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
	protected GasBudgetExtra radiatorsBathroom;

	@Schema(description = "Total Price without VAT.")
	public String getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}

	@JsonIgnore
	public void setTotalBudget(
		UnsafeSupplier<String, Exception> totalBudgetUnsafeSupplier) {

		try {
			totalBudget = totalBudgetUnsafeSupplier.get();
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
	protected String totalBudget;

	@Schema(description = "Total price with VAT")
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

	@Schema(description = "VAT amount")
	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	@JsonIgnore
	public void setVat(UnsafeSupplier<String, Exception> vatUnsafeSupplier) {
		try {
			vat = vatUnsafeSupplier.get();
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
	protected String vat;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GasBudget)) {
			return false;
		}

		GasBudget gasBudget = (GasBudget)object;

		return Objects.equals(toString(), gasBudget.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (baseBudget != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"baseBudget\": ");

			sb.append("\"");

			sb.append(_escape(baseBudget));

			sb.append("\"");
		}

		if (bonus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bonus\": ");

			sb.append("\"");

			sb.append(_escape(bonus));

			sb.append("\"");
		}

		if (connectDeviceToKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"connectDeviceToKitchen\": ");

			sb.append(String.valueOf(connectDeviceToKitchen));
		}

		if (controllHeatingFloor != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"controllHeatingFloor\": ");

			sb.append(String.valueOf(controllHeatingFloor));
		}

		if (convertDeviceKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"convertDeviceKitchen\": ");

			sb.append(String.valueOf(convertDeviceKitchen));
		}

		if (equipment != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"equipment\": ");

			sb.append("\"");

			sb.append(_escape(equipment));

			sb.append("\"");
		}

		if (hasVentilationGrill != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hasVentilationGrill\": ");

			sb.append(String.valueOf(hasVentilationGrill));
		}

		if (metersBoilerToWindow != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"metersBoilerToWindow\": ");

			sb.append(String.valueOf(metersBoilerToWindow));
		}

		if (metersWaterIntake != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"metersWaterIntake\": ");

			sb.append(String.valueOf(metersWaterIntake));
		}

		if (proposedPack != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"proposedPack\": ");

			sb.append("\"");

			sb.append(_escape(proposedPack));

			sb.append("\"");
		}

		if (radiatorsBathroom != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"radiatorsBathroom\": ");

			sb.append(String.valueOf(radiatorsBathroom));
		}

		if (totalBudget != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalBudget\": ");

			sb.append("\"");

			sb.append(_escape(totalBudget));

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

		if (vat != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"vat\": ");

			sb.append("\"");

			sb.append(_escape(vat));

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