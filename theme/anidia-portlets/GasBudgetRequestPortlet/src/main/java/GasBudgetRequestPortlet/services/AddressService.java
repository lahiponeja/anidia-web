package GasBudgetRequestPortlet.services;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import GasBudgetRequestPortlet.dto.AddressDTO;


public class AddressService {

	public List<AddressDTO> getMunicipalties() throws IOException {
		//BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/com/myorg/foo.jpg"));
		InputStream inputStream = getClass().getResourceAsStream("municipalities.csv");
		//FileInputStream inputStream = new FileInputStream(new File("/GasBudgetRequestPortlet/src/main/resources/municipalities.csv"));
		BufferedReader csvFile = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";

		List<AddressDTO> addresses = new ArrayList<AddressDTO>();


		while((line = csvFile.readLine()) != null) {
			String[] columns = line.split(";", -1);
			addresses.add(new AddressDTO(columns[0], columns[1], columns[2], columns[3]));
		}

		return addresses;

	}

}
