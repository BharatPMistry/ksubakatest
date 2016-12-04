package com.ksubaka;

import java.io.UnsupportedEncodingException;
import java.net.*;

public class EncodingTools {

	public static URL encodeUrl(URL siteUrl) {
		URL url = null;

		try {
			URI uri = new URI(siteUrl.getProtocol(), siteUrl.getUserInfo(), siteUrl.getHost(), siteUrl.getPort(), siteUrl.getPath(), siteUrl.getQuery(), siteUrl.getRef());
			url = uri.toURL();
		} catch (MalformedURLException error) {
			error.printStackTrace();
		} catch (URISyntaxException error) {
			error.printStackTrace();
		}

		return url;
	}

	public static String endcodeFilmName(String filmName) {
		String encodeFileName = null;
		try {
			encodeFileName = URLEncoder.encode(filmName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeFileName;
	}

}
