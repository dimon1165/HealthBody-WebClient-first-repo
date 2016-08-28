package edu.softserveinc.healthbody.webclient.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoogleFitUtils {
	/**
	 * Make Post call to Google Fit
	 **/
	public static String post(String access_token, Long startTimeMillis, Long endTimeMillis) throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate");
		String param = "{\"aggregateBy\":[{\"dataTypeName\": \"com.google.step_count.delta\","
				+ "\"dataSourceId\": \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"}],"
				+ "\"bucketByTime\": { \"durationMillis\": 888888888886400000 },\"startTimeMillis\":" + startTimeMillis
				+ ",\"endTimeMillis\":" + endTimeMillis + "}";
		StringEntity entity = new StringEntity(param, "UTF-8");
		request.setEntity(entity);
		request.addHeader("Content-type", "application/json");
		request.addHeader("Accept", "application/json");
		request.addHeader("Authorization", "Bearer " + access_token);
		HttpResponse response = httpClient.execute(request);

		log.info("Code :" + response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 200) {
			return "empty";
		} else {
			InputStream body = response.getEntity().getContent();
			BufferedReader in = new BufferedReader(new InputStreamReader(body));
			String line;
			String output = "";
			while ((line = in.readLine()) != null) {
				output += line;
			}
			return output;
		}
	}
}
