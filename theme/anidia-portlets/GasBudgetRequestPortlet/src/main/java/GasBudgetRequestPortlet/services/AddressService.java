package GasBudgetRequestPortlet.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import GasBudgetRequestPortlet.dto.AddressDTO;

public class AddressService {

	public List<AddressDTO> getMunicipalties() throws IOException {
		InputStream inputStream = getClass().getResourceAsStream("municipalities.csv");
		BufferedReader csvFile = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";

		List<AddressDTO> addresses = new ArrayList<AddressDTO>();

		while ((line = csvFile.readLine()) != null) {
			String[] columns = line.split(";", -1);
			addresses.add(new AddressDTO(columns[0], columns[1], columns[2], columns[3]));
		}

		return addresses;

	}

}
