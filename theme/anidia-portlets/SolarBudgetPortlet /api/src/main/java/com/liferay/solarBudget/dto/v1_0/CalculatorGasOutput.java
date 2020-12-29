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

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@GraphQLName("CalculatorGasOutput")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "CalculatorGasOutput")
public class CalculatorGasOutput {

	@Schema(description = "BaseBadget calculator gas output")
	public String getBaseBadget() {
		return baseBadget;
	}

	public void setBaseBadget(String baseBadget) {
		this.baseBadget = baseBadget;
	}

	@JsonIgnore
	public void setBaseBadget(
		UnsafeSupplier<String, Exception> baseBadgetUnsafeSupplier) {

		try {
			baseBadget = baseBadgetUnsafeSupplier.get();
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
	protected String baseBadget;

	@Schema(description = "Bonus calculator gas output")
	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	@JsonIgnore
	public void setBonus(
		UnsafeSupplier<String, Exception> bonusUnsafeSupplier) {

		try {
			bonus = bonusUnsafeSupplier.get();
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
	protected String bonus;

	@Schema(description = "Equipment calculator gas output")
	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	@JsonIgnore
	public void setEquipment(
		UnsafeSupplier<String, Exception> equipmentUnsafeSupplier) {

		try {
			equipment = equipmentUnsafeSupplier.get();
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
	protected String equipment;

	@Schema(description = "Calculator gas output extras")
	@Valid
	public CalculatorGasOutputExtras getExtras() {
		return extras;
	}

	public void setExtras(CalculatorGasOutputExtras extras) {
		this.extras = extras;
	}

	@JsonIgnore
	public void setExtras(
		UnsafeSupplier<CalculatorGasOutputExtras, Exception>
			extrasUnsafeSupplier) {

		try {
			extras = extrasUnsafeSupplier.get();
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
	protected CalculatorGasOutputExtras extras;

	@Schema(description = "Iva21 calculator gas output")
	public String getIva21() {
		return iva21;
	}

	public void setIva21(String iva21) {
		this.iva21 = iva21;
	}

	@JsonIgnore
	public void setIva21(
		UnsafeSupplier<String, Exception> iva21UnsafeSupplier) {

		try {
			iva21 = iva21UnsafeSupplier.get();
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
	protected String iva21;

	@Schema(description = "ProposedPack calculator gas output")
	public String getProposedPack() {
		return proposedPack;
	}

	public void setProposedPack(String proposedPack) {
		this.proposedPack = proposedPack;
	}

	@JsonIgnore
	public void setProposedPack(
		UnsafeSupplier<String, Exception> proposedPackUnsafeSupplier) {

		try {
			proposedPack = proposedPackUnsafeSupplier.get();
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
	protected String proposedPack;

	@Schema(description = "TotalBudget calculator gas output")
	public String getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}

	@JsonIgnore
	public void setTotalBudget(
		UnsafeSupplier<String, Exception> totalBudgetUnsafeSupplier) {

		try {
			totalBudget = totalBudgetUnsafeSupplier.get();
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
	protected String totalBudget;

	@Schema(description = "TotalPVP calculator gas output")
	public String getTotalPVP() {
		return totalPVP;
	}

	public void setTotalPVP(String totalPVP) {
		this.totalPVP = totalPVP;
	}

	@JsonIgnore
	public void setTotalPVP(
		UnsafeSupplier<String, Exception> totalPVPUnsafeSupplier) {

		try {
			totalPVP = totalPVPUnsafeSupplier.get();
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
	protected String totalPVP;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CalculatorGasOutput)) {
			return false;
		}

		CalculatorGasOutput calculatorGasOutput = (CalculatorGasOutput)object;

		return Objects.equals(toString(), calculatorGasOutput.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (baseBadget != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"baseBadget\": ");

			sb.append("\"");

			sb.append(_escape(baseBadget));

			sb.append("\"");
		}

		if (bonus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bonus\": ");

			sb.append("\"");

			sb.append(_escape(bonus));

			sb.append("\"");
		}

		if (equipment != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"equipment\": ");

			sb.append("\"");

			sb.append(_escape(equipment));

			sb.append("\"");
		}

		if (extras != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"extras\": ");

			sb.append(String.valueOf(extras));
		}

		if (iva21 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"iva21\": ");

			sb.append("\"");

			sb.append(_escape(iva21));

			sb.append("\"");
		}

		if (proposedPack != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"proposedPack\": ");

			sb.append("\"");

			sb.append(_escape(proposedPack));

			sb.append("\"");
		}

		if (totalBudget != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalBudget\": ");

			sb.append("\"");

			sb.append(_escape(totalBudget));

			sb.append("\"");
		}

		if (totalPVP != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalPVP\": ");

			sb.append("\"");

			sb.append(_escape(totalPVP));

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