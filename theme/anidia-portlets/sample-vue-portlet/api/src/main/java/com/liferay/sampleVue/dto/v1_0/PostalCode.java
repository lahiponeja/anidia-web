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
@GraphQLName("PostalCode")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PostalCode")
public class PostalCode {

	@Schema(
		description = "Municipality identifier (3 first digits from postal code)"
	)
	public String getMunicipalityID() {
		return municipalityID;
	}

	public void setMunicipalityID(String municipalityID) {
		this.municipalityID = municipalityID;
	}

	@JsonIgnore
	public void setMunicipalityID(
		UnsafeSupplier<String, Exception> municipalityIDUnsafeSupplier) {

		try {
			municipalityID = municipalityIDUnsafeSupplier.get();
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
	protected String municipalityID;

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
	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	@JsonIgnore
	public void setPostal_code(
		UnsafeSupplier<String, Exception> postal_codeUnsafeSupplier) {

		try {
			postal_code = postal_codeUnsafeSupplier.get();
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
	protected String postal_code;

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

		if (municipalityID != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"municipalityID\": ");

			sb.append("\"");

			sb.append(_escape(municipalityID));

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

		if (postal_code != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"postal_code\": ");

			sb.append("\"");

			sb.append(_escape(postal_code));

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