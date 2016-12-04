package com.ksubaka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RestAPICall {

	static public String makeGetRestApiCallToService(URL searchUrl) {
		String output;
		StringBuilder jsonResponse = new StringBuilder();

		try {
			HttpURLConnection conn = (HttpURLConnection) searchUrl.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			while ((output = br.readLine()) != null) {
				jsonResponse.append(output);
				jsonResponse.append("\n");
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonResponse.toString();
	}

}
