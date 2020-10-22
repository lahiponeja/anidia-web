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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@GraphQLName("ConsumptionSaving")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ConsumptionSaving")
public class ConsumptionSaving {

	@Schema(
		description = "The current cost paid by the client as estimated by the service"
	)
	public String getCurrentCost() {
		return currentCost;
	}

	public void setCurrentCost(String currentCost) {
		this.currentCost = currentCost;
	}

	@JsonIgnore
	public void setCurrentCost(
		UnsafeSupplier<String, Exception> currentCostUnsafeSupplier) {

		try {
			currentCost = currentCostUnsafeSupplier.get();
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
	protected String currentCost;

	@Schema(description = "The future cost paid by the client with natural gas")
	public String getFutureCost() {
		return futureCost;
	}

	public void setFutureCost(String futureCost) {
		this.futureCost = futureCost;
	}

	@JsonIgnore
	public void setFutureCost(
		UnsafeSupplier<String, Exception> futureCostUnsafeSupplier) {

		try {
			futureCost = futureCostUnsafeSupplier.get();
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
	protected String futureCost;

	@Schema(
		description = "The required consumption as calculated by the service"
	)
	public String getRequriedConsumption() {
		return requriedConsumption;
	}

	public void setRequriedConsumption(String requriedConsumption) {
		this.requriedConsumption = requriedConsumption;
	}

	@JsonIgnore
	public void setRequriedConsumption(
		UnsafeSupplier<String, Exception> requriedConsumptionUnsafeSupplier) {

		try {
			requriedConsumption = requriedConsumptionUnsafeSupplier.get();
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
	protected String requriedConsumption;

	@Schema(description = "Amount of money saved")
	public String getSaving() {
		return saving;
	}

	public void setSaving(String saving) {
		this.saving = saving;
	}

	@JsonIgnore
	public void setSaving(
		UnsafeSupplier<String, Exception> savingUnsafeSupplier) {

		try {
			saving = savingUnsafeSupplier.get();
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
	protected String saving;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ConsumptionSaving)) {
			return false;
		}

		ConsumptionSaving consumptionSaving = (ConsumptionSaving)object;

		return Objects.equals(toString(), consumptionSaving.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (currentCost != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"currentCost\": ");

			sb.append("\"");

			sb.append(_escape(currentCost));

			sb.append("\"");
		}

		if (futureCost != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"futureCost\": ");

			sb.append("\"");

			sb.append(_escape(futureCost));

			sb.append("\"");
		}

		if (requriedConsumption != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requriedConsumption\": ");

			sb.append("\"");

			sb.append(_escape(requriedConsumption));

			sb.append("\"");
		}

		if (saving != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"saving\": ");

			sb.append("\"");

			sb.append(_escape(saving));

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