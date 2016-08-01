package edu.softserveinc.healthbody.webclient.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionREST {

	private static volatile HttpURLConnectionREST instance;

	private HttpURLConnectionREST() {
	}

	public static HttpURLConnectionREST getInstance() {
		if (instance == null) {
			synchronized (HttpURLConnectionREST.class) {
				if (instance == null) {
					instance = new HttpURLConnectionREST();
				}
			}
		}
		return instance;
	}

	public void sendGet(ControllerRESTStrategy controllerREST) throws IOException {
		URL url = controllerREST.createRESTRequest();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		StringBuilder sb = new StringBuilder();
		String output;
		System.out.println("Output REST for REST method - " + controllerREST.getClass().getName() + " \n");
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		System.out.println(sb.toString());
		br.close();
		connection.disconnect();
	}

}
