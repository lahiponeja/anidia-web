package com.liferay.gasComparator.dto.v1_0;

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
@GraphQLName("GasConsumptionComparison")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "GasConsumptionComparison")
public class GasConsumptionComparison {

	@Schema(description = "Real consumption by the user")
	public String getConsumptionRequired() {
		return consumptionRequired;
	}

	public void setConsumptionRequired(String consumptionRequired) {
		this.consumptionRequired = consumptionRequired;
	}

	@JsonIgnore
	public void setConsumptionRequired(
		UnsafeSupplier<String, Exception> consumptionRequiredUnsafeSupplier) {

		try {
			consumptionRequired = consumptionRequiredUnsafeSupplier.get();
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
	protected String consumptionRequired;

	@Schema(description = "Current cost for the user")
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

	@Schema(description = "Future cost with GN")
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

	@Schema(description = "Saving with GN")
	public String getSavings() {
		return savings;
	}

	public void setSavings(String savings) {
		this.savings = savings;
	}

	@JsonIgnore
	public void setSavings(
		UnsafeSupplier<String, Exception> savingsUnsafeSupplier) {

		try {
			savings = savingsUnsafeSupplier.get();
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
	protected String savings;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GasConsumptionComparison)) {
			return false;
		}

		GasConsumptionComparison gasConsumptionComparison =
			(GasConsumptionComparison)object;

		return Objects.equals(toString(), gasConsumptionComparison.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (consumptionRequired != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consumptionRequired\": ");

			sb.append("\"");

			sb.append(_escape(consumptionRequired));

			sb.append("\"");
		}

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

		if (savings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"savings\": ");

			sb.append("\"");

			sb.append(_escape(savings));

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