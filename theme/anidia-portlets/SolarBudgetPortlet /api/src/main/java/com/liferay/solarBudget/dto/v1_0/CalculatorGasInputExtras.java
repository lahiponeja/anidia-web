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
@GraphQLName("CalculatorGasInputExtras")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "CalculatorGasInputExtras")
public class CalculatorGasInputExtras {

	@Schema(description = "ConnectDeviceToKitchen calculator gas")
	public String getConnectDeviceToKitchen() {
		return connectDeviceToKitchen;
	}

	public void setConnectDeviceToKitchen(String connectDeviceToKitchen) {
		this.connectDeviceToKitchen = connectDeviceToKitchen;
	}

	@JsonIgnore
	public void setConnectDeviceToKitchen(
		UnsafeSupplier<String, Exception>
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
	protected String connectDeviceToKitchen;

	@Schema(description = "ControllHeatingFloor calculator gas")
	public String getControllHeatingFloor() {
		return controllHeatingFloor;
	}

	public void setControllHeatingFloor(String controllHeatingFloor) {
		this.controllHeatingFloor = controllHeatingFloor;
	}

	@JsonIgnore
	public void setControllHeatingFloor(
		UnsafeSupplier<String, Exception> controllHeatingFloorUnsafeSupplier) {

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
	protected String controllHeatingFloor;

	@Schema(description = "ConvertDeviceKitchen calculator gas")
	public String getConvertDeviceKitchen() {
		return convertDeviceKitchen;
	}

	public void setConvertDeviceKitchen(String convertDeviceKitchen) {
		this.convertDeviceKitchen = convertDeviceKitchen;
	}

	@JsonIgnore
	public void setConvertDeviceKitchen(
		UnsafeSupplier<String, Exception> convertDeviceKitchenUnsafeSupplier) {

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
	protected String convertDeviceKitchen;

	@Schema(description = "HasVentilationGrill calculator gas")
	public String getHasVentilationGrill() {
		return hasVentilationGrill;
	}

	public void setHasVentilationGrill(String hasVentilationGrill) {
		this.hasVentilationGrill = hasVentilationGrill;
	}

	@JsonIgnore
	public void setHasVentilationGrill(
		UnsafeSupplier<String, Exception> hasVentilationGrillUnsafeSupplier) {

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
	protected String hasVentilationGrill;

	@Schema(description = "MetersBoilerToWindow calculator gas")
	public Integer getMetersBoilerToWindow() {
		return metersBoilerToWindow;
	}

	public void setMetersBoilerToWindow(Integer metersBoilerToWindow) {
		this.metersBoilerToWindow = metersBoilerToWindow;
	}

	@JsonIgnore
	public void setMetersBoilerToWindow(
		UnsafeSupplier<Integer, Exception> metersBoilerToWindowUnsafeSupplier) {

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
	protected Integer metersBoilerToWindow;

	@Schema(description = "MetersWaterIntake calculator gas")
	public String getMetersWaterIntake() {
		return metersWaterIntake;
	}

	public void setMetersWaterIntake(String metersWaterIntake) {
		this.metersWaterIntake = metersWaterIntake;
	}

	@JsonIgnore
	public void setMetersWaterIntake(
		UnsafeSupplier<String, Exception> metersWaterIntakeUnsafeSupplier) {

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
	protected String metersWaterIntake;

	@Schema(description = "RadiatorsBathroom calculator gas")
	public String getRadiatorsBathroom() {
		return radiatorsBathroom;
	}

	public void setRadiatorsBathroom(String radiatorsBathroom) {
		this.radiatorsBathroom = radiatorsBathroom;
	}

	@JsonIgnore
	public void setRadiatorsBathroom(
		UnsafeSupplier<String, Exception> radiatorsBathroomUnsafeSupplier) {

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
	protected String radiatorsBathroom;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CalculatorGasInputExtras)) {
			return false;
		}

		CalculatorGasInputExtras calculatorGasInputExtras =
			(CalculatorGasInputExtras)object;

		return Objects.equals(toString(), calculatorGasInputExtras.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (connectDeviceToKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"connectDeviceToKitchen\": ");

			sb.append("\"");

			sb.append(_escape(connectDeviceToKitchen));

			sb.append("\"");
		}

		if (controllHeatingFloor != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"controllHeatingFloor\": ");

			sb.append("\"");

			sb.append(_escape(controllHeatingFloor));

			sb.append("\"");
		}

		if (convertDeviceKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"convertDeviceKitchen\": ");

			sb.append("\"");

			sb.append(_escape(convertDeviceKitchen));

			sb.append("\"");
		}

		if (hasVentilationGrill != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hasVentilationGrill\": ");

			sb.append("\"");

			sb.append(_escape(hasVentilationGrill));

			sb.append("\"");
		}

		if (metersBoilerToWindow != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"metersBoilerToWindow\": ");

			sb.append(metersBoilerToWindow);
		}

		if (metersWaterIntake != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"metersWaterIntake\": ");

			sb.append("\"");

			sb.append(_escape(metersWaterIntake));

			sb.append("\"");
		}

		if (radiatorsBathroom != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"radiatorsBathroom\": ");

			sb.append("\"");

			sb.append(_escape(radiatorsBathroom));

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