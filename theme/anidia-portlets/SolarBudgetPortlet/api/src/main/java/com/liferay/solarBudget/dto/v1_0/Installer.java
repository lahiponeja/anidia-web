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
@GraphQLName("Installer")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Installer")
public class Installer {

	@Schema(description = "Installer code")
	public String getInstallerCode() {
		return installerCode;
	}

	public void setInstallerCode(String installerCode) {
		this.installerCode = installerCode;
	}

	@JsonIgnore
	public void setInstallerCode(
		UnsafeSupplier<String, Exception> installerCodeUnsafeSupplier) {

		try {
			installerCode = installerCodeUnsafeSupplier.get();
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
	protected String installerCode;

	@Schema(description = "Installer Name")
	public String getInstallerName() {
		return installerName;
	}

	public void setInstallerName(String installerName) {
		this.installerName = installerName;
	}

	@JsonIgnore
	public void setInstallerName(
		UnsafeSupplier<String, Exception> installerNameUnsafeSupplier) {

		try {
			installerName = installerNameUnsafeSupplier.get();
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
	protected String installerName;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Installer)) {
			return false;
		}

		Installer installer = (Installer)object;

		return Objects.equals(toString(), installer.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (installerCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"installerCode\": ");

			sb.append("\"");

			sb.append(_escape(installerCode));

			sb.append("\"");
		}

		if (installerName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"installerName\": ");

			sb.append("\"");

			sb.append(_escape(installerName));

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