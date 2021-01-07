package com.liferay.solarBudget.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
@GraphQLName("ElectricalAppliance")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ElectricalAppliance")
public class ElectricalAppliance {

	@GraphQLName("ElectricalAppliance1")
	public static enum ElectricalAppliance1 {

		VITROCERMICA("Vitrocerámica"), FRIGORFICO("Frigorífico"),
		LAVADORA("Lavadora"), HORNO("Horno"), TELEVISIN("Televisión"),
		ALUMBRADO("Alumbrado"), ORDENADOR("Ordenador"),
		MICROONDAS("Microondas"), AIRE_ACONDICIONADO("Aire acondicionado"),
		TERMO_ELCTRICO("Termo Eléctrico"),
		PATINETE_ELCTRICO("Patinete eléctrico"), DOMTICA("Domótica"),
		COCHE_ELCTRICO("Coche eléctrico"), SECADORA("Secadora"),
		DEPURADORA_PISCINA("Depuradora Piscina"), ASCENSOR("Ascensor"),
		BOMBRA_DE_PRESIN("Bombra de presión"),
		ASPIRADORA_INTELIGENTE("Aspiradora inteligente");

		@JsonCreator
		public static ElectricalAppliance1 create(String value) {
			for (ElectricalAppliance1 electricalAppliance1 : values()) {
				if (Objects.equals(electricalAppliance1.getValue(), value)) {
					return electricalAppliance1;
				}
			}

			return null;
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private ElectricalAppliance1(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("ElectricalAppliance2")
	public static enum ElectricalAppliance2 {

		VITROCERMICA("Vitrocerámica"), FRIGORFICO("Frigorífico"),
		LAVADORA("Lavadora"), HORNO("Horno"), TELEVISIN("Televisión"),
		ALUMBRADO("Alumbrado"), ORDENADOR("Ordenador"),
		MICROONDAS("Microondas"), AIRE_ACONDICIONADO("Aire acondicionado"),
		TERMO_ELCTRICO("Termo Eléctrico"),
		PATINETE_ELCTRICO("Patinete eléctrico"), DOMTICA("Domótica"),
		COCHE_ELCTRICO("Coche eléctrico"), SECADORA("Secadora"),
		DEPURADORA_PISCINA("Depuradora Piscina"), ASCENSOR("Ascensor"),
		BOMBRA_DE_PRESIN("Bombra de presión"),
		ASPIRADORA_INTELIGENTE("Aspiradora inteligente");

		@JsonCreator
		public static ElectricalAppliance2 create(String value) {
			for (ElectricalAppliance2 electricalAppliance2 : values()) {
				if (Objects.equals(electricalAppliance2.getValue(), value)) {
					return electricalAppliance2;
				}
			}

			return null;
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private ElectricalAppliance2(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("ElectricalAppliance3")
	public static enum ElectricalAppliance3 {

		VITROCERMICA("Vitrocerámica"), FRIGORFICO("Frigorífico"),
		LAVADORA("Lavadora"), HORNO("Horno"), TELEVISIN("Televisión"),
		ALUMBRADO("Alumbrado"), ORDENADOR("Ordenador"),
		MICROONDAS("Microondas"), AIRE_ACONDICIONADO("Aire acondicionado"),
		TERMO_ELCTRICO("Termo Eléctrico"),
		PATINETE_ELCTRICO("Patinete eléctrico"), DOMTICA("Domótica"),
		COCHE_ELCTRICO("Coche eléctrico"), SECADORA("Secadora"),
		DEPURADORA_PISCINA("Depuradora Piscina"), ASCENSOR("Ascensor"),
		BOMBRA_DE_PRESIN("Bombra de presión"),
		ASPIRADORA_INTELIGENTE("Aspiradora inteligente");

		@JsonCreator
		public static ElectricalAppliance3 create(String value) {
			for (ElectricalAppliance3 electricalAppliance3 : values()) {
				if (Objects.equals(electricalAppliance3.getValue(), value)) {
					return electricalAppliance3;
				}
			}

			return null;
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private ElectricalAppliance3(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Schema
	@Valid
	public ElectricalAppliance1 getElectricalAppliance1() {
		return electricalAppliance1;
	}

	@JsonIgnore
	public String getElectricalAppliance1AsString() {
		if (electricalAppliance1 == null) {
			return null;
		}

		return electricalAppliance1.toString();
	}

	public void setElectricalAppliance1(
		ElectricalAppliance1 electricalAppliance1) {

		this.electricalAppliance1 = electricalAppliance1;
	}

	@JsonIgnore
	public void setElectricalAppliance1(
		UnsafeSupplier<ElectricalAppliance1, Exception>
			electricalAppliance1UnsafeSupplier) {

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
	protected ElectricalAppliance1 electricalAppliance1;

	@Schema
	@Valid
	public ElectricalAppliance2 getElectricalAppliance2() {
		return electricalAppliance2;
	}

	@JsonIgnore
	public String getElectricalAppliance2AsString() {
		if (electricalAppliance2 == null) {
			return null;
		}

		return electricalAppliance2.toString();
	}

	public void setElectricalAppliance2(
		ElectricalAppliance2 electricalAppliance2) {

		this.electricalAppliance2 = electricalAppliance2;
	}

	@JsonIgnore
	public void setElectricalAppliance2(
		UnsafeSupplier<ElectricalAppliance2, Exception>
			electricalAppliance2UnsafeSupplier) {

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
	protected ElectricalAppliance2 electricalAppliance2;

	@Schema
	@Valid
	public ElectricalAppliance3 getElectricalAppliance3() {
		return electricalAppliance3;
	}

	@JsonIgnore
	public String getElectricalAppliance3AsString() {
		if (electricalAppliance3 == null) {
			return null;
		}

		return electricalAppliance3.toString();
	}

	public void setElectricalAppliance3(
		ElectricalAppliance3 electricalAppliance3) {

		this.electricalAppliance3 = electricalAppliance3;
	}

	@JsonIgnore
	public void setElectricalAppliance3(
		UnsafeSupplier<ElectricalAppliance3, Exception>
			electricalAppliance3UnsafeSupplier) {

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
	protected ElectricalAppliance3 electricalAppliance3;

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

			sb.append(electricalAppliance1);

			sb.append("\"");
		}

		if (electricalAppliance2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricalAppliance2\": ");

			sb.append("\"");

			sb.append(electricalAppliance2);

			sb.append("\"");
		}

		if (electricalAppliance3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"electricalAppliance3\": ");

			sb.append("\"");

			sb.append(electricalAppliance3);

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