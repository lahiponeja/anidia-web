package GasBudgetRequestPortlet.dto;

import com.liferay.portal.kernel.json.JSON;

import java.io.Serializable;

public class AddressDTO implements Serializable {

	private String ProvinceCode;
	private String MunicipalityCode;
	private String PostalCode;
	private String MunicipalityName;


	public AddressDTO() {
		super();
	}

	public AddressDTO(String provinceCode, String municipalityCode, String postalCode, String municipalityName) {
		super();
		ProvinceCode = provinceCode;
		MunicipalityCode = municipalityCode;
		PostalCode = postalCode;
		MunicipalityName = municipalityName;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 4672569118563075927L;

	@JSON
	public String getProvinceCode() {
		return ProvinceCode;
	}

	@JSON
	public String getMunicipalityName() {
		return MunicipalityName;
	}

	@JSON
	public String getPostalCode() {
		return PostalCode;
	}

	@JSON
	public String getMunicipalityCode() {
		return MunicipalityCode;
	}

}
