package edu.softserveinc.healthbody.webclient.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

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
			String fitData = "";
			while ((line = in.readLine()) != null) {
				fitData += line;
			}
			return fitData;
		}
	}

	public static String getStepCount(String fitData) {
		String stepCount = "0";
		if ("empty".equals(fitData)) {
			log.info("You don't have steps , we will set your step count \"0\"");
		} else {
			log.info(fitData);
			JSONObject googleSteps = new JSONObject(fitData);
			if (googleSteps.isNull("bucket")) {
				log.info("Sorry your info about steps is empty");
			} else {
				stepCount = googleSteps.getJSONArray("bucket").getJSONObject(0).getJSONArray("dataset").getJSONObject(0)
						.getJSONArray("point").getJSONObject(0).getJSONArray("value").getJSONObject(0).get("intVal")
						.toString();
			}
		}
		log.info("Your steps count :" + stepCount);
		return stepCount;

	}
}
