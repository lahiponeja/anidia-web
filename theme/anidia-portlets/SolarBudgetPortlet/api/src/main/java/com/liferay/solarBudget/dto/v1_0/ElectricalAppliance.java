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
@GraphQLName("ElectricalAppliance")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ElectricalAppliance")
public class ElectricalAppliance {

	@Schema
	public String getElectricalAppliance1() {
		return electricalAppliance1;
	}

	public void setElectricalAppliance1(String electricalAppliance1) {
		this.electricalAppliance1 = electricalAppliance1;
	}

	@JsonIgnore
	public void setElectricalAppliance1(
		UnsafeSupplier<String, Exception> electricalAppliance1UnsafeSupplier) {

		try {
			electricalAppliance1 = electricalAppliance1UnsafeSupplier.get();
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
	protected String electricalAppliance1;

	@Schema
	public String getElectricalAppliance2() {
		return electricalAppliance2;
	}

	public void setElectricalAppliance2(String electricalAppliance2) {
		this.electricalAppliance2 = electricalAppliance2;
	}

	@JsonIgnore
	public void setElectricalAppliance2(
		UnsafeSupplier<String, Exception> electricalAppliance2UnsafeSupplier) {

		try {
			electricalAppliance2 = electricalAppliance2UnsafeSupplier.get();
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
	protected String electricalAppliance2;

	@Schema
	public String getElectricalAppliance3() {
		return electricalAppliance3;
	}

	public void setElectricalAppliance3(String electricalAppliance3) {
		this.electricalAppliance3 = electricalAppliance3;
	}

	@JsonIgnore
	public void setElectricalAppliance3(
		UnsafeSupplier<String, Exception> electricalAppliance3UnsafeSupplier) {

		try {
			electricalAppliance3 = electricalAppliance3UnsafeSupplier.get();
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
	protected String electricalAppliance3;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ElectricalAppliance)) {
			return false;
		}

		ElectricalAppliance electricalAppliance = (ElectricalAppliance)object;

		return Objects.equals(toString(), electricalAppliance.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (electricalAppliance1 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricalAppliance1\": ");

			sb.append("\"");

			sb.append(_escape(electricalAppliance1));

			sb.append("\"");
		}

		if (electricalAppliance2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricalAppliance2\": ");

			sb.append("\"");

			sb.append(_escape(electricalAppliance2));

			sb.append("\"");
		}

		if (electricalAppliance3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricalAppliance3\": ");

			sb.append("\"");

			sb.append(_escape(electricalAppliance3));

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