package com.liferay.gasBudget.internal.resource.v1_0;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.gasBudget.dto.v1_0.PostalCode;
import com.liferay.gasBudget.resource.v1_0.PostalCodeResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author David Brenes
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/postal-code.properties", scope = ServiceScope.PROTOTYPE, service = PostalCodeResource.class)
public class PostalCodeResourceImpl extends BasePostalCodeResourceImpl {
	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/gas-budget/v1.0/postal-codes' -u
	 * 'test@liferay.com:test'
	 */
	@Override
	public Page<PostalCode> getPostalCodesPage() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("postal-codes.csv");
		BufferedReader csvFile = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";

		List<PostalCode> postalCodes = new ArrayList<PostalCode>();

		while ((line = csvFile.readLine()) != null) {
			// each lines has only the postal codes
			String[] columns = line.split(";", -1);
			PostalCode postalCode = new PostalCode();
			postalCode.setPostalCode(columns[0]);
			postalCodes.add(postalCode);
		}
		return Page.of(postalCodes);
	}

	@Override
	public Page<PostalCode> getMunicipalityPostalCodePage(
			@NotNull String postalCode) throws Exception {

		InputStream inputStream = getClass().getResourceAsStream("municipalities.csv");
		BufferedReader csvFile = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";

		List<PostalCode> postalCodes = new ArrayList<PostalCode>();

		while ((line = csvFile.readLine()) != null) {
			// each lines has the following format: CodProvincia;CodMunicipio;CodigoPostal;Municipio
			String[] columns = line.split(";", -1);
			if(columns[2].equals(postalCode)) {
				PostalCode tempPostalCode = new PostalCode();
				tempPostalCode.setProvinceId(columns[0]);
				tempPostalCode.setMunicipalityId(columns[1]);
				tempPostalCode.setPostalCode(columns[2]);
				tempPostalCode.setMunicipalityName(columns[3]);
				postalCodes.add(tempPostalCode);
			}
		}
		return Page.of(postalCodes);
	}


}