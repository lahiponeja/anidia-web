package com.liferay.sampleVue.dto.v1_0;

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
@GraphQLName("PersonalData")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PersonalData")
public class PersonalData {

	@GraphQLName("ProdInterest")
	public static enum ProdInterest {

		GAS("gas"), SOLAR("solar"), MANTENIMIENTO("mantenimiento");

		@JsonCreator
		public static ProdInterest create(String value) {
			for (ProdInterest prodInterest : values()) {
				if (Objects.equals(prodInterest.getValue(), value)) {
					return prodInterest;
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

		private ProdInterest(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Schema(
		description = "Wether the lead has accepted commercial communications"
	)
	public Boolean getAcceptNotCom() {
		return acceptNotCom;
	}

	public void setAcceptNotCom(Boolean acceptNotCom) {
		this.acceptNotCom = acceptNotCom;
	}

	@JsonIgnore
	public void setAcceptNotCom(
		UnsafeSupplier<Boolean, Exception> acceptNotComUnsafeSupplier) {

		try {
			acceptNotCom = acceptNotComUnsafeSupplier.get();
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
	protected Boolean acceptNotCom;

	@Schema(description = "Email of the lead")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public void setEmail(
		UnsafeSupplier<String, Exception> emailUnsafeSupplier) {

		try {
			email = emailUnsafeSupplier.get();
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
	protected String email;

	@Schema(description = "Estate of the lead")
	@Valid
	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	@JsonIgnore
	public void setEstate(
		UnsafeSupplier<Estate, Exception> estateUnsafeSupplier) {

		try {
			estate = estateUnsafeSupplier.get();
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
	protected Estate estate;

	@Schema(description = "First name of the lead")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonIgnore
	public void setFirstName(
		UnsafeSupplier<String, Exception> firstNameUnsafeSupplier) {

		try {
			firstName = firstNameUnsafeSupplier.get();
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
	protected String firstName;

	@Schema(description = "Last name of the lead")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public void setLastName(
		UnsafeSupplier<String, Exception> lastNameUnsafeSupplier) {

		try {
			lastName = lastNameUnsafeSupplier.get();
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
	protected String lastName;

	@Schema(description = "Phone (including international prefix)")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonIgnore
	public void setPhone(
		UnsafeSupplier<String, Exception> phoneUnsafeSupplier) {

		try {
			phone = phoneUnsafeSupplier.get();
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
	protected String phone;

	@Schema(description = "Postal Code of the Lead")
	@Valid
	public PostalCode getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}

	@JsonIgnore
	public void setPostalCode(
		UnsafeSupplier<PostalCode, Exception> postalCodeUnsafeSupplier) {

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
	protected PostalCode postalCode;

	@Schema(description = "Product of Interest (gas, solar, mantenimiento)")
	@Valid
	public ProdInterest getProdInterest() {
		return prodInterest;
	}

	@JsonIgnore
	public String getProdInterestAsString() {
		if (prodInterest == null) {
			return null;
		}

		return prodInterest.toString();
	}

	public void setProdInterest(ProdInterest prodInterest) {
		this.prodInterest = prodInterest;
	}

	@JsonIgnore
	public void setProdInterest(
		UnsafeSupplier<ProdInterest, Exception> prodInterestUnsafeSupplier) {

		try {
			prodInterest = prodInterestUnsafeSupplier.get();
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
	protected ProdInterest prodInterest;

	@Schema(description = "Full property of the Lead")
	@Valid
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@JsonIgnore
	public void setProperty(
		UnsafeSupplier<Property, Exception> propertyUnsafeSupplier) {

		try {
			property = propertyUnsafeSupplier.get();
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
	protected Property property;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PersonalData)) {
			return false;
		}

		PersonalData personalData = (PersonalData)object;

		return Objects.equals(toString(), personalData.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (acceptNotCom != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"acceptNotCom\": ");

			sb.append(acceptNotCom);
		}

		if (email != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(email));

			sb.append("\"");
		}

		if (estate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"estate\": ");

			sb.append(String.valueOf(estate));
		}

		if (firstName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(firstName));

			sb.append("\"");
		}

		if (lastName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(lastName));

			sb.append("\"");
		}

		if (phone != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"phone\": ");

			sb.append("\"");

			sb.append(_escape(phone));

			sb.append("\"");
		}

		if (postalCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"postalCode\": ");

			sb.append(String.valueOf(postalCode));
		}

		if (prodInterest != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"prodInterest\": ");

			sb.append("\"");

			sb.append(prodInterest);

			sb.append("\"");
		}

		if (property != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"property\": ");

			sb.append(String.valueOf(property));
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