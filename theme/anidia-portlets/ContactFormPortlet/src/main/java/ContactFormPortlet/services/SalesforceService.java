package ContactFormPortlet.services;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class SalesforceService {
    private Log log = LogFactoryUtil.getLog(SalesforceService.class.getName());

	public String sendContactData(){
        log.info("####Inside service method");
        return ("Desde dentro!");
    }

    public String getSalesforceToken() {

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
	}
}
