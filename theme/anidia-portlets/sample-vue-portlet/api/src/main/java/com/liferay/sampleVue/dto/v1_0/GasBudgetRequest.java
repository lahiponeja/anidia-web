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
@GraphQLName("GasBudgetRequest")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "GasBudgetRequest")
public class GasBudgetRequest {

	@GraphQLName("AcsUse")
	public static enum AcsUse {

		NO_PROCEDE("No Procede"), TERMO_ELCTRICO("Termo eléctrico"),
		BUTANO("Butano"), PROPANO("Propano"), GASLEO("Gasóleo"),
		CARBN("Carbón"), OTRO("Otro");

		@JsonCreator
		public static AcsUse create(String value) {
			for (AcsUse acsUse : values()) {
				if (Objects.equals(acsUse.getValue(), value)) {
					return acsUse;
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

		private AcsUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("BoilerLocation")
	public static enum BoilerLocation {

		LAVADERO_TERRAZA("Lavadero/Terraza"), COCINA("Cocina"), BAO("Baño");

		@JsonCreator
		public static BoilerLocation create(String value) {
			for (BoilerLocation boilerLocation : values()) {
				if (Objects.equals(boilerLocation.getValue(), value)) {
					return boilerLocation;
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

		private BoilerLocation(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("GasNaturalUse")
	public static enum GasNaturalUse {

		SOLO_ACS("solo ACS"), ACS_COCINA("ACS+Cocina"),
		ACS_CALEFACCIN("ACS+Calefacción"),
		ACS_COCINA_CALEFACCIN("ACS+Cocina+Calefacción");

		@JsonCreator
		public static GasNaturalUse create(String value) {
			for (GasNaturalUse gasNaturalUse : values()) {
				if (Objects.equals(gasNaturalUse.getValue(), value)) {
					return gasNaturalUse;
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

		private GasNaturalUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("HeatingUse")
	public static enum HeatingUse {

		NO_PROCEDE("No Procede"), RADIADORES_ELCTRICOS("Radiadores eléctricos"),
		BUTANO("Butano"), PROPANO("Propano"), GASLEO("Gasóleo"),
		CARBN("Carbón"), OTRO("Otro");

		@JsonCreator
		public static HeatingUse create(String value) {
			for (HeatingUse heatingUse : values()) {
				if (Objects.equals(heatingUse.getValue(), value)) {
					return heatingUse;
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

		private HeatingUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("HouseType")
	public static enum HouseType {

		UNIFAMILIAR("Unifamiliar"), BLOQUE_DE_PISOS("Bloque de pisos");

		@JsonCreator
		public static HouseType create(String value) {
			for (HouseType houseType : values()) {
				if (Objects.equals(houseType.getValue(), value)) {
					return houseType;
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

		private HouseType(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("KitchenUse")
	public static enum KitchenUse {

		NO_PROCEDE("No Procede"), ELCTRICO("Eléctrico"), BUTANO("Butano"),
		PROPANO("Propano"), GASLEO("Gasóleo"), CARBN("Carbón"), OTRO("Otro");

		@JsonCreator
		public static KitchenUse create(String value) {
			for (KitchenUse kitchenUse : values()) {
				if (Objects.equals(kitchenUse.getValue(), value)) {
					return kitchenUse;
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

		private KitchenUse(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("MetersWaterIntake")
	public static enum MetersWaterIntake {

		M0("m.0"), M1("m.1"), M2("m.2"), M3("m.3"), M4("m.4");

		@JsonCreator
		public static MetersWaterIntake create(String value) {
			for (MetersWaterIntake metersWaterIntake : values()) {
				if (Objects.equals(metersWaterIntake.getValue(), value)) {
					return metersWaterIntake;
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

		private MetersWaterIntake(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("PersonsWater")
	public static enum PersonsWater {

		HASTA_2("Hasta 2"), ENTRE_3_4("Entre 3-4"), MS_DE_4("Más de 4");

		@JsonCreator
		public static PersonsWater create(String value) {
			for (PersonsWater personsWater : values()) {
				if (Objects.equals(personsWater.getValue(), value)) {
					return personsWater;
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

		private PersonsWater(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("PropertyMeters")
	public static enum PropertyMeters {

		HASTA_100M2("Hasta 100m2"), DE_100M2_A_180M2("De 100m2 a 180m2"),
		MAS_DE_180M2("Mas de 180m2");

		@JsonCreator
		public static PropertyMeters create(String value) {
			for (PropertyMeters propertyMeters : values()) {
				if (Objects.equals(propertyMeters.getValue(), value)) {
					return propertyMeters;
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

		private PropertyMeters(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Schema(description = "Intended use for ACS")
	@Valid
	public AcsUse getAcsUse() {
		return acsUse;
	}

	@JsonIgnore
	public String getAcsUseAsString() {
		if (acsUse == null) {
			return null;
		}

		return acsUse.toString();
	}

	public void setAcsUse(AcsUse acsUse) {
		this.acsUse = acsUse;
	}

	@JsonIgnore
	public void setAcsUse(
		UnsafeSupplier<AcsUse, Exception> acsUseUnsafeSupplier) {

		try {
			acsUse = acsUseUnsafeSupplier.get();
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
	protected AcsUse acsUse;

	@Schema(description = "Number of bathrooms")
	public Integer getBathroomNumber() {
		return bathroomNumber;
	}

	public void setBathroomNumber(Integer bathroomNumber) {
		this.bathroomNumber = bathroomNumber;
	}

	@JsonIgnore
	public void setBathroomNumber(
		UnsafeSupplier<Integer, Exception> bathroomNumberUnsafeSupplier) {

		try {
			bathroomNumber = bathroomNumberUnsafeSupplier.get();
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
	protected Integer bathroomNumber;

	@Schema(description = "Location of the boiler")
	@Valid
	public BoilerLocation getBoilerLocation() {
		return boilerLocation;
	}

	@JsonIgnore
	public String getBoilerLocationAsString() {
		if (boilerLocation == null) {
			return null;
		}

		return boilerLocation.toString();
	}

	public void setBoilerLocation(BoilerLocation boilerLocation) {
		this.boilerLocation = boilerLocation;
	}

	@JsonIgnore
	public void setBoilerLocation(
		UnsafeSupplier<BoilerLocation, Exception>
			boilerLocationUnsafeSupplier) {

		try {
			boilerLocation = boilerLocationUnsafeSupplier.get();
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
	protected BoilerLocation boilerLocation;

	@Schema(description = "Has the client a connect device in the kitchen?")
	public Boolean getConnectDeviceToKitchen() {
		return connectDeviceToKitchen;
	}

	public void setConnectDeviceToKitchen(Boolean connectDeviceToKitchen) {
		this.connectDeviceToKitchen = connectDeviceToKitchen;
	}

	@JsonIgnore
	public void setConnectDeviceToKitchen(
		UnsafeSupplier<Boolean, Exception>
			connectDeviceToKitchenUnsafeSupplier) {

		try {
			connectDeviceToKitchen = connectDeviceToKitchenUnsafeSupplier.get();
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
	protected Boolean connectDeviceToKitchen;

	@Schema(
		description = "Has the client controlled heating in the whole floor?"
	)
	public Boolean getControllHeatingFloor() {
		return controllHeatingFloor;
	}

	public void setControllHeatingFloor(Boolean controllHeatingFloor) {
		this.controllHeatingFloor = controllHeatingFloor;
	}

	@JsonIgnore
	public void setControllHeatingFloor(
		UnsafeSupplier<Boolean, Exception> controllHeatingFloorUnsafeSupplier) {

		try {
			controllHeatingFloor = controllHeatingFloorUnsafeSupplier.get();
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
	protected Boolean controllHeatingFloor;

	@Schema(description = "Has the client a convert device in the kitchen?")
	public Boolean getConvertDeviceKitchen() {
		return convertDeviceKitchen;
	}

	public void setConvertDeviceKitchen(Boolean convertDeviceKitchen) {
		this.convertDeviceKitchen = convertDeviceKitchen;
	}

	@JsonIgnore
	public void setConvertDeviceKitchen(
		UnsafeSupplier<Boolean, Exception> convertDeviceKitchenUnsafeSupplier) {

		try {
			convertDeviceKitchen = convertDeviceKitchenUnsafeSupplier.get();
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
	protected Boolean convertDeviceKitchen;

	@Schema(description = "Number of floors in the property")
	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	@JsonIgnore
	public void setFloorNumber(
		UnsafeSupplier<Integer, Exception> floorNumberUnsafeSupplier) {

		try {
			floorNumber = floorNumberUnsafeSupplier.get();
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
	protected Integer floorNumber;

	@Schema(description = "Intented use of the gas")
	@Valid
	public GasNaturalUse getGasNaturalUse() {
		return gasNaturalUse;
	}

	@JsonIgnore
	public String getGasNaturalUseAsString() {
		if (gasNaturalUse == null) {
			return null;
		}

		return gasNaturalUse.toString();
	}

	public void setGasNaturalUse(GasNaturalUse gasNaturalUse) {
		this.gasNaturalUse = gasNaturalUse;
	}

	@JsonIgnore
	public void setGasNaturalUse(
		UnsafeSupplier<GasNaturalUse, Exception> gasNaturalUseUnsafeSupplier) {

		try {
			gasNaturalUse = gasNaturalUseUnsafeSupplier.get();
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
	protected GasNaturalUse gasNaturalUse;

	@Schema(description = "Has the client a ventilation grill?")
	public Boolean getHasVentilationGrill() {
		return hasVentilationGrill;
	}

	public void setHasVentilationGrill(Boolean hasVentilationGrill) {
		this.hasVentilationGrill = hasVentilationGrill;
	}

	@JsonIgnore
	public void setHasVentilationGrill(
		UnsafeSupplier<Boolean, Exception> hasVentilationGrillUnsafeSupplier) {

		try {
			hasVentilationGrill = hasVentilationGrillUnsafeSupplier.get();
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
	protected Boolean hasVentilationGrill;

	@Schema(description = "Intended use of heating")
	@Valid
	public HeatingUse getHeatingUse() {
		return heatingUse;
	}

	@JsonIgnore
	public String getHeatingUseAsString() {
		if (heatingUse == null) {
			return null;
		}

		return heatingUse.toString();
	}

	public void setHeatingUse(HeatingUse heatingUse) {
		this.heatingUse = heatingUse;
	}

	@JsonIgnore
	public void setHeatingUse(
		UnsafeSupplier<HeatingUse, Exception> heatingUseUnsafeSupplier) {

		try {
			heatingUse = heatingUseUnsafeSupplier.get();
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
	protected HeatingUse heatingUse;

	@Schema(description = "Kind of house")
	@Valid
	public HouseType getHouseType() {
		return houseType;
	}

	@JsonIgnore
	public String getHouseTypeAsString() {
		if (houseType == null) {
			return null;
		}

		return houseType.toString();
	}

	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}

	@JsonIgnore
	public void setHouseType(
		UnsafeSupplier<HouseType, Exception> houseTypeUnsafeSupplier) {

		try {
			houseType = houseTypeUnsafeSupplier.get();
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
	protected HouseType houseType;

	@Schema(description = "Intended use for the Kitchen")
	@Valid
	public KitchenUse getKitchenUse() {
		return kitchenUse;
	}

	@JsonIgnore
	public String getKitchenUseAsString() {
		if (kitchenUse == null) {
			return null;
		}

		return kitchenUse.toString();
	}

	public void setKitchenUse(KitchenUse kitchenUse) {
		this.kitchenUse = kitchenUse;
	}

	@JsonIgnore
	public void setKitchenUse(
		UnsafeSupplier<KitchenUse, Exception> kitchenUseUnsafeSupplier) {

		try {
			kitchenUse = kitchenUseUnsafeSupplier.get();
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
	protected KitchenUse kitchenUse;

	@Schema(description = "Distance between the boiler and the window")
	public Integer getMetersBoilerToWindow() {
		return metersBoilerToWindow;
	}

	public void setMetersBoilerToWindow(Integer metersBoilerToWindow) {
		this.metersBoilerToWindow = metersBoilerToWindow;
	}

	@JsonIgnore
	public void setMetersBoilerToWindow(
		UnsafeSupplier<Integer, Exception> metersBoilerToWindowUnsafeSupplier) {

		try {
			metersBoilerToWindow = metersBoilerToWindowUnsafeSupplier.get();
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
	protected Integer metersBoilerToWindow;

	@Schema(description = "Distance to the water intake")
	@Valid
	public MetersWaterIntake getMetersWaterIntake() {
		return metersWaterIntake;
	}

	@JsonIgnore
	public String getMetersWaterIntakeAsString() {
		if (metersWaterIntake == null) {
			return null;
		}

		return metersWaterIntake.toString();
	}

	public void setMetersWaterIntake(MetersWaterIntake metersWaterIntake) {
		this.metersWaterIntake = metersWaterIntake;
	}

	@JsonIgnore
	public void setMetersWaterIntake(
		UnsafeSupplier<MetersWaterIntake, Exception>
			metersWaterIntakeUnsafeSupplier) {

		try {
			metersWaterIntake = metersWaterIntakeUnsafeSupplier.get();
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
	protected MetersWaterIntake metersWaterIntake;

	@Schema(description = "Number of persons that use the waters")
	@Valid
	public PersonsWater getPersonsWater() {
		return personsWater;
	}

	@JsonIgnore
	public String getPersonsWaterAsString() {
		if (personsWater == null) {
			return null;
		}

		return personsWater.toString();
	}

	public void setPersonsWater(PersonsWater personsWater) {
		this.personsWater = personsWater;
	}

	@JsonIgnore
	public void setPersonsWater(
		UnsafeSupplier<PersonsWater, Exception> personsWaterUnsafeSupplier) {

		try {
			personsWater = personsWaterUnsafeSupplier.get();
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
	protected PersonsWater personsWater;

	@Schema(description = "Postal Code of the client")
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

	@Schema(description = "Size of the property in meters")
	@Valid
	public PropertyMeters getPropertyMeters() {
		return propertyMeters;
	}

	@JsonIgnore
	public String getPropertyMetersAsString() {
		if (propertyMeters == null) {
			return null;
		}

		return propertyMeters.toString();
	}

	public void setPropertyMeters(PropertyMeters propertyMeters) {
		this.propertyMeters = propertyMeters;
	}

	@JsonIgnore
	public void setPropertyMeters(
		UnsafeSupplier<PropertyMeters, Exception>
			propertyMetersUnsafeSupplier) {

		try {
			propertyMeters = propertyMetersUnsafeSupplier.get();
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
	protected PropertyMeters propertyMeters;

	@Schema(description = "Number of the radiators in the bathroom.")
	public Integer getRadiatorsBathroom() {
		return radiatorsBathroom;
	}

	public void setRadiatorsBathroom(Integer radiatorsBathroom) {
		this.radiatorsBathroom = radiatorsBathroom;
	}

	@JsonIgnore
	public void setRadiatorsBathroom(
		UnsafeSupplier<Integer, Exception> radiatorsBathroomUnsafeSupplier) {

		try {
			radiatorsBathroom = radiatorsBathroomUnsafeSupplier.get();
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
	protected Integer radiatorsBathroom;

	@Schema(description = "Number of rooms")
	public Integer getStaysNumber() {
		return staysNumber;
	}

	public void setStaysNumber(Integer staysNumber) {
		this.staysNumber = staysNumber;
	}

	@JsonIgnore
	public void setStaysNumber(
		UnsafeSupplier<Integer, Exception> staysNumberUnsafeSupplier) {

		try {
			staysNumber = staysNumberUnsafeSupplier.get();
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
	protected Integer staysNumber;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GasBudgetRequest)) {
			return false;
		}

		GasBudgetRequest gasBudgetRequest = (GasBudgetRequest)object;

		return Objects.equals(toString(), gasBudgetRequest.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (acsUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"acsUse\": ");

			sb.append("\"");

			sb.append(acsUse);

			sb.append("\"");
		}

		if (bathroomNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bathroomNumber\": ");

			sb.append(bathroomNumber);
		}

		if (boilerLocation != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"boilerLocation\": ");

			sb.append("\"");

			sb.append(boilerLocation);

			sb.append("\"");
		}

		if (connectDeviceToKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"connectDeviceToKitchen\": ");

			sb.append(connectDeviceToKitchen);
		}

		if (controllHeatingFloor != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"controllHeatingFloor\": ");

			sb.append(controllHeatingFloor);
		}

		if (convertDeviceKitchen != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"convertDeviceKitchen\": ");

			sb.append(convertDeviceKitchen);
		}

		if (floorNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"floorNumber\": ");

			sb.append(floorNumber);
		}

		if (gasNaturalUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gasNaturalUse\": ");

			sb.append("\"");

			sb.append(gasNaturalUse);

			sb.append("\"");
		}

		if (hasVentilationGrill != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hasVentilationGrill\": ");

			sb.append(hasVentilationGrill);
		}

		if (heatingUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"heatingUse\": ");

			sb.append("\"");

			sb.append(heatingUse);

			sb.append("\"");
		}

		if (houseType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"houseType\": ");

			sb.append("\"");

			sb.append(houseType);

			sb.append("\"");
		}

		if (kitchenUse != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"kitchenUse\": ");

			sb.append("\"");

			sb.append(kitchenUse);

			sb.append("\"");
		}

		if (metersBoilerToWindow != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"metersBoilerToWindow\": ");

			sb.append(metersBoilerToWindow);
		}

		if (metersWaterIntake != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"metersWaterIntake\": ");

			sb.append("\"");

			sb.append(metersWaterIntake);

			sb.append("\"");
		}

		if (personsWater != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"personsWater\": ");

			sb.append("\"");

			sb.append(personsWater);

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

		if (propertyMeters != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"propertyMeters\": ");

			sb.append("\"");

			sb.append(propertyMeters);

			sb.append("\"");
		}

		if (radiatorsBathroom != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"radiatorsBathroom\": ");

			sb.append(radiatorsBathroom);
		}

		if (staysNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"staysNumber\": ");

			sb.append(staysNumber);
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