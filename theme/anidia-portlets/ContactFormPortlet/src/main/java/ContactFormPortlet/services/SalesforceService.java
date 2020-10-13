package ContactFormPortlet.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
/*import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
*/

public class SalesforceService {
	
	static String SALESFORCE_TOKEN_URL = System.getenv().get("SALESFORCE_TOKEN_URL");
	static String SALESFORCE_PASSWORD = System.getenv().get("SALESFORCE_PASSWORD");
	static String SALESFORCE_CLIENT_SECRET = System.getenv().get("SALESFORCE_CLIENT_SECRET");
	static String SALESFORCE_CLIENT_ID = System.getenv().get("SALESFORCE_CLIENT_ID");
	static String SALESFORCE_USERNAME = System.getenv().get("SALESFORCE_USERNAME");

	public String sendContactData(){
        return (SALESFORCE_TOKEN_URL);
    }

/*    public String getSalesforceToken() {

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(SALESFORCE_TOKEN_URL);
		urlBuilder.append("?");
		urlBuilder.append("grant_type=password");
		urlBuilder.append("&client_id=");
		urlBuilder.append(SALESFORCE_CLIENT_ID);
		urlBuilder.append("&client_secret=");
		urlBuilder.append(SALESFORCE_CLIENT_SECRET);
		urlBuilder.append("&username=");
		urlBuilder.append(SALESFORCE_USERNAME);
		urlBuilder.append("&password=");
		urlBuilder.append(SALESFORCE_PASSWORD);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create(urlBuilder.toString())).
			POST(HttpRequest.BodyPublishers.noBody()).
			build();

		HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

	    JSONObject json;
		try {
			json = new JSONObject(response.body());
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		try {
			return json.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
