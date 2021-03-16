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
@GraphQLName("Property")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Property")
public class Property {

	@Schema(description = "Full address for the property")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonIgnore
	public void setAddress(
		UnsafeSupplier<String, Exception> addressUnsafeSupplier) {

		try {
			address = addressUnsafeSupplier.get();
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
	protected String address;

	@Schema(description = "Block of the property (e.g. 2)")
	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	@JsonIgnore
	public void setBlock(
		UnsafeSupplier<String, Exception> blockUnsafeSupplier) {

		try {
			block = blockUnsafeSupplier.get();
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
	protected String block;

	@Schema(description = "Door in the flor of the property (e.g. C)")
	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	@JsonIgnore
	public void setDoor(UnsafeSupplier<String, Exception> doorUnsafeSupplier) {
		try {
			door = doorUnsafeSupplier.get();
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
	protected String door;

	@Schema(description = "Floor of the property (e.g. 2º)")
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@JsonIgnore
	public void setFloor(
		UnsafeSupplier<String, Exception> floorUnsafeSupplier) {

		try {
			floor = floorUnsafeSupplier.get();
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
	protected String floor;

	@Schema(description = "Ladder of the property (e.g. Izq)")
	public String getLadder() {
		return ladder;
	}

	public void setLadder(String ladder) {
		this.ladder = ladder;
	}

	@JsonIgnore
	public void setLadder(
		UnsafeSupplier<String, Exception> ladderUnsafeSupplier) {

		try {
			ladder = ladderUnsafeSupplier.get();
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
	protected String ladder;

	@Schema(description = "Property unique identifier")
	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	@JsonIgnore
	public void setPropertyId(
		UnsafeSupplier<String, Exception> propertyIdUnsafeSupplier) {

		try {
			propertyId = propertyIdUnsafeSupplier.get();
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
	protected String propertyId;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Property)) {
			return false;
		}

		Property property = (Property)object;

		return Objects.equals(toString(), property.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (address != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"address\": ");

			sb.append("\"");

			sb.append(_escape(address));

			sb.append("\"");
		}

		if (block != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"block\": ");

			sb.append("\"");

			sb.append(_escape(block));

			sb.append("\"");
		}

		if (door != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"door\": ");

			sb.append("\"");

			sb.append(_escape(door));

			sb.append("\"");
		}

		if (floor != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"floor\": ");

			sb.append("\"");

			sb.append(_escape(floor));

			sb.append("\"");
		}

		if (ladder != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ladder\": ");

			sb.append("\"");

			sb.append(_escape(ladder));

			sb.append("\"");
		}

		if (propertyId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"propertyId\": ");

			sb.append("\"");

			sb.append(_escape(propertyId));

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