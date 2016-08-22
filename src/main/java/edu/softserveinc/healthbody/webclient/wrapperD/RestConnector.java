package edu.softserveinc.healthbody.webclient.wrapperD;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestConnector {

	private static volatile RestConnector instance;

	private RestConnector() {
	}

	public static RestConnector getInstance() {
		if (instance == null) {
			synchronized (RestConnector.class) {
				if (instance == null) {
					instance = new RestConnector();
				}
			}
		}
		return instance;
	}

	public JsonArray sendRequestGet(URL url) throws IOException {
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.setRequestMethod("GET");
		request.setRequestProperty("Accept", "application/json");
			if (request.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + request.getResponseCode());
			}
				JsonParser jsonParser = new JsonParser();
				JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) request.getContent(),StandardCharsets.UTF_8));
				JsonArray jsonArray = jsonElement.getAsJsonArray();
				
				request.disconnect();
				
		return jsonArray;
	}
}