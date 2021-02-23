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
@GraphQLName("Estate")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Estate")
public class Estate {

	@Schema(description = "Type of the address (street, avenue...)")
	public String getAddressKind() {
		return addressKind;
	}

	public void setAddressKind(String addressKind) {
		this.addressKind = addressKind;
	}

	@JsonIgnore
	public void setAddressKind(
		UnsafeSupplier<String, Exception> addressKindUnsafeSupplier) {

		try {
			addressKind = addressKindUnsafeSupplier.get();
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
	protected String addressKind;

	@Schema(description = "Name of the address")
	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@JsonIgnore
	public void setAddressName(
		UnsafeSupplier<String, Exception> addressNameUnsafeSupplier) {

		try {
			addressName = addressNameUnsafeSupplier.get();
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
	protected String addressName;

	@Schema(description = "Annex to the gate number (e.g BIS in 2 BIS)")
	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	@JsonIgnore
	public void setAnnex(
		UnsafeSupplier<String, Exception> annexUnsafeSupplier) {

		try {
			annex = annexUnsafeSupplier.get();
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
	protected String annex;

	@Schema(description = "Unique identifier of the gate")
	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	@JsonIgnore
	public void setGateId(
		UnsafeSupplier<String, Exception> gateIdUnsafeSupplier) {

		try {
			gateId = gateIdUnsafeSupplier.get();
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
	protected String gateId;

	@Schema(description = "Number of the gate")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@JsonIgnore
	public void setNumber(
		UnsafeSupplier<String, Exception> numberUnsafeSupplier) {

		try {
			number = numberUnsafeSupplier.get();
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
	protected String number;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Estate)) {
			return false;
		}

		Estate estate = (Estate)object;

		return Objects.equals(toString(), estate.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (addressKind != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"addressKind\": ");

			sb.append("\"");

			sb.append(_escape(addressKind));

			sb.append("\"");
		}

		if (addressName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"addressName\": ");

			sb.append("\"");

			sb.append(_escape(addressName));

			sb.append("\"");
		}

		if (annex != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"annex\": ");

			sb.append("\"");

			sb.append(_escape(annex));

			sb.append("\"");
		}

		if (gateId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gateId\": ");

			sb.append("\"");

			sb.append(_escape(gateId));

			sb.append("\"");
		}

		if (number != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"number\": ");

			sb.append("\"");

			sb.append(_escape(number));

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