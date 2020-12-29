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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@GraphQLName("CalculatorGasOutputExtras")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "CalculatorGasOutputExtras")
public class CalculatorGasOutputExtras {

	@Schema(description = "ConnectDeviceToKitchen calculator gas")
	public String getConnectDeviceToKitchen() {
		return ConnectDeviceToKitchen;
	}

	public void setConnectDeviceToKitchen(String ConnectDeviceToKitchen) {
		this.ConnectDeviceToKitchen = ConnectDeviceToKitchen;
	}

	@JsonIgnore
	public void setConnectDeviceToKitchen(
		UnsafeSupplier<String, Exception>
			ConnectDeviceToKitchenUnsafeSupplier) {

		try {
			ConnectDeviceToKitchen = ConnectDeviceToKitchenUnsafeSupplier.get();
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
	protected String ConnectDeviceToKitchen;

	@Schema(description = "ControllHeatingFloor gas output")
	public String getControllHeatingFloor() {
		return ControllHeatingFloor;
	}

	public void setControllHeatingFloor(String ControllHeatingFloor) {
		this.ControllHeatingFloor = ControllHeatingFloor;
	}

	@JsonIgnore
	public void setControllHeatingFloor(
		UnsafeSupplier<String, Exception> ControllHeatingFloorUnsafeSupplier) {

		try {
			ControllHeatingFloor = ControllHeatingFloorUnsafeSupplier.get();
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
	protected String ControllHeatingFloor;

	@Schema(description = "ConvertDeviceKitchen gas output")
	public String getConvertDeviceKitchen() {
		return ConvertDeviceKitchen;
	}

	public void setConvertDeviceKitchen(String ConvertDeviceKitchen) {
		this.ConvertDeviceKitchen = ConvertDeviceKitchen;
	}

	@JsonIgnore
	public void setConvertDeviceKitchen(
		UnsafeSupplier<String, Exception> ConvertDeviceKitchenUnsafeSupplier) {

		try {
			ConvertDeviceKitchen = ConvertDeviceKitchenUnsafeSupplier.get();
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
	protected String ConvertDeviceKitchen;

	@Schema(description = "ExtraTotalPrice gas output")
	public String getExtraTotalPrice() {
		return ExtraTotalPrice;
	}

	public void setExtraTotalPrice(String ExtraTotalPrice) {
		this.ExtraTotalPrice = ExtraTotalPrice;
	}

	@JsonIgnore
	public void setExtraTotalPrice(
		UnsafeSupplier<String, Exception> ExtraTotalPriceUnsafeSupplier) {

		try {
			ExtraTotalPrice = ExtraTotalPriceUnsafeSupplier.get();
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
	protected String ExtraTotalPrice;

	@Schema(description = "HasVentilationGrill gas output")
	public String getHasVentilationGrill() {
		return HasVentilationGrill;
	}

	public void setHasVentilationGrill(String HasVentilationGrill) {
		this.HasVentilationGrill = HasVentilationGrill;
	}

	@JsonIgnore
	public void setHasVentilationGrill(
		UnsafeSupplier<String, Exception> HasVentilationGrillUnsafeSupplier) {

		try {
			HasVentilationGrill = HasVentilationGrillUnsafeSupplier.get();
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
	protected String HasVentilationGrill;

	@Schema(description = "MetersBoilerToWindow gas output")
	public String getMetersBoilerToWindow() {
		return MetersBoilerToWindow;
	}

	public void setMetersBoilerToWindow(String MetersBoilerToWindow) {
		this.MetersBoilerToWindow = MetersBoilerToWindow;
	}

	@JsonIgnore
	public void setMetersBoilerToWindow(
		UnsafeSupplier<String, Exception> MetersBoilerToWindowUnsafeSupplier) {

		try {
			MetersBoilerToWindow = MetersBoilerToWindowUnsafeSupplier.get();
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
	protected String MetersBoilerToWindow;

	@Schema(description = "MetersWaterIntake gas output")
	public String getMetersWaterIntake() {
		return MetersWaterIntake;
	}

	public void setMetersWaterIntake(String MetersWaterIntake) {
		this.MetersWaterIntake = MetersWaterIntake;
	}

	@JsonIgnore
	public void setMetersWaterIntake(
		UnsafeSupplier<String, Exception> MetersWaterIntakeUnsafeSupplier) {

		try {
			MetersWaterIntake = MetersWaterIntakeUnsafeSupplier.get();
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
	protected String MetersWaterIntake;

	@Schema(description = "RadiatorsBathroom gas output")
	public String getRadiatorsBathroom() {
		return RadiatorsBathroom;
	}

	public void setRadiatorsBathroom(String RadiatorsBathroom) {
		this.RadiatorsBathroom = RadiatorsBathroom;
	}

	@JsonIgnore
	public void setRadiatorsBathroom(
		UnsafeSupplier<String, Exception> RadiatorsBathroomUnsafeSupplier) {

		try {
			RadiatorsBathroom = RadiatorsBathroomUnsafeSupplier.get();
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
	protected String RadiatorsBathroom;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CalculatorGasOutputExtras)) {
			return false;
		}

		CalculatorGasOutputExtras calculatorGasOutputExtras =
			(CalculatorGasOutputExtras)object;

		return Objects.equals(toString(), calculatorGasOutputExtras.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (ConnectDeviceToKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ConnectDeviceToKitchen\": ");

			sb.append("\"");

			sb.append(_escape(ConnectDeviceToKitchen));

			sb.append("\"");
		}

		if (ControllHeatingFloor != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ControllHeatingFloor\": ");

			sb.append("\"");

			sb.append(_escape(ControllHeatingFloor));

			sb.append("\"");
		}

		if (ConvertDeviceKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ConvertDeviceKitchen\": ");

			sb.append("\"");

			sb.append(_escape(ConvertDeviceKitchen));

			sb.append("\"");
		}

		if (ExtraTotalPrice != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ExtraTotalPrice\": ");

			sb.append("\"");

			sb.append(_escape(ExtraTotalPrice));

			sb.append("\"");
		}

		if (HasVentilationGrill != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"HasVentilationGrill\": ");

			sb.append("\"");

			sb.append(_escape(HasVentilationGrill));

			sb.append("\"");
		}

		if (MetersBoilerToWindow != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"MetersBoilerToWindow\": ");

			sb.append("\"");

			sb.append(_escape(MetersBoilerToWindow));

			sb.append("\"");
		}

		if (MetersWaterIntake != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"MetersWaterIntake\": ");

			sb.append("\"");

			sb.append(_escape(MetersWaterIntake));

			sb.append("\"");
		}

		if (RadiatorsBathroom != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"RadiatorsBathroom\": ");

			sb.append("\"");

			sb.append(_escape(RadiatorsBathroom));

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