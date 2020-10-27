package com.liferay.gasBudget.dto.v1_0;

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
@GraphQLName("PostalCode")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PostalCode")
public class PostalCode {

	@Schema(description = "INE Municipality identifier")
	public String getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}

	@JsonIgnore
	public void setMunicipalityId(
		UnsafeSupplier<String, Exception> municipalityIdUnsafeSupplier) {

		try {
			municipalityId = municipalityIdUnsafeSupplier.get();
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
	protected String municipalityId;

	@Schema(description = "Municipality name")
	public String getMunicipalityName() {
		return municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	@JsonIgnore
	public void setMunicipalityName(
		UnsafeSupplier<String, Exception> municipalityNameUnsafeSupplier) {

		try {
			municipalityName = municipalityNameUnsafeSupplier.get();
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
	protected String municipalityName;

	@Schema(description = "Postal Code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonIgnore
	public void setPostalCode(
		UnsafeSupplier<String, Exception> postalCodeUnsafeSupplier) {

		try {
			postalCode = postalCodeUnsafeSupplier.get();
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
	protected String postalCode;

	@Schema(description = "Municipality name (2 first digits from postal code)")
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@JsonIgnore
	public void setProvinceId(
		UnsafeSupplier<String, Exception> provinceIdUnsafeSupplier) {

		try {
			provinceId = provinceIdUnsafeSupplier.get();
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
	protected String provinceId;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PostalCode)) {
			return false;
		}

		PostalCode postalCode = (PostalCode)object;

		return Objects.equals(toString(), postalCode.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (municipalityId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"municipalityId\": ");

			sb.append("\"");

			sb.append(_escape(municipalityId));

			sb.append("\"");
		}

		if (municipalityName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"municipalityName\": ");

			sb.append("\"");

			sb.append(_escape(municipalityName));

			sb.append("\"");
		}

		if (postalCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"postalCode\": ");

			sb.append("\"");

			sb.append(_escape(postalCode));

			sb.append("\"");
		}

		if (provinceId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"provinceId\": ");

			sb.append("\"");

			sb.append(_escape(provinceId));

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