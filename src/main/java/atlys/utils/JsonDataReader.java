package atlys.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.ApplicationDataAPI;

public class JsonDataReader {
	private static final String API_URL = configReader.getProperty("api.url");

	public static ApplicationDataAPI getDataFromApi() {
		try {
			URL url = new URL(API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder jsonResponse = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonResponse.append(line);
			}
			reader.close();
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonResponse.toString(), ApplicationDataAPI.class);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to fetch or parse JSON from API");
		}

	}
}
