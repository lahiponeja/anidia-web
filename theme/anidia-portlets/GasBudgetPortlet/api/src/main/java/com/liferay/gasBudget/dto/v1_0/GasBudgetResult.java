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

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author David Brenes
 * @generated
 */
@Generated("")
@GraphQLName("GasBudgetResult")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "GasBudgetResult")
public class GasBudgetResult {

	@Schema
	@Valid
	public GasBudget[] getGasBudgets() {
		return GasBudgets;
	}

	public void setGasBudgets(GasBudget[] GasBudgets) {
		this.GasBudgets = GasBudgets;
	}

	@JsonIgnore
	public void setGasBudgets(
		UnsafeSupplier<GasBudget[], Exception> GasBudgetsUnsafeSupplier) {

		try {
			GasBudgets = GasBudgetsUnsafeSupplier.get();
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
	protected GasBudget[] GasBudgets;

	@Schema
	@Valid
	public GasBudget getPrincipalBudget() {
		return PrincipalBudget;
	}

	public void setPrincipalBudget(GasBudget PrincipalBudget) {
		this.PrincipalBudget = PrincipalBudget;
	}

	@JsonIgnore
	public void setPrincipalBudget(
		UnsafeSupplier<GasBudget, Exception> PrincipalBudgetUnsafeSupplier) {

		try {
			PrincipalBudget = PrincipalBudgetUnsafeSupplier.get();
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
	protected GasBudget PrincipalBudget;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GasBudgetResult)) {
			return false;
		}

		GasBudgetResult gasBudgetResult = (GasBudgetResult)object;

		return Objects.equals(toString(), gasBudgetResult.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (GasBudgets != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"GasBudgets\": ");

			sb.append("[");

			for (int i = 0; i < GasBudgets.length; i++) {
				sb.append(String.valueOf(GasBudgets[i]));

				if ((i + 1) < GasBudgets.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (PrincipalBudget != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"PrincipalBudget\": ");

			sb.append(String.valueOf(PrincipalBudget));
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