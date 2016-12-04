package com.ksubaka;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.*;
import java.util.ArrayList;

public class FilmTmdbEntertainmentSite extends FilmEntertainmentSite {

	private final String URL_SEARCH_FILM_BY_NAME = "https://api.themoviedb.org/3/search/movie";

	private final String API_KEY = "api_key=6b71d948d6bacbb349f59cdbfaa89273";

	private final String QUERY_PARAMETER = "query";

	public FilmTmdbEntertainmentSite() {
	}

	//
	//This is the main function called by the outside world.  All the functions in this class are supporting functions
	//
	public ArrayList<BaseFilm> getFilmEntertainmentSiteResults(String searchFilmName) {

		URL searchUrl = buildSearchUrl(searchFilmName);

		String jsonRresponseFromCall = RestAPICall.makeGetRestApiCallToService(searchUrl);

		ArrayList<BaseFilm> listOfFilms = parseTheJsonFormatStringAndGetTheBaseFilmResults(jsonRresponseFromCall);

		return listOfFilms;
	}

	private URL buildSearchUrl(String searchFilmName) {
		URL domain = null;
		URL url = null;

		String encodedFilmName = EncodingTools.endcodeFilmName(searchFilmName);
		try {
			domain = new URL(URL_SEARCH_FILM_BY_NAME);
			url = new URL(domain + "?" + API_KEY + "&" + QUERY_PARAMETER + "=" + encodedFilmName);
			url = EncodingTools.encodeUrl(url);
		} catch (MalformedURLException error) {
			error.printStackTrace();
		}
		return url;
	}

	private ArrayList<BaseFilm> parseTheJsonFormatStringAndGetTheBaseFilmResults(String jsonStringToParse) {
		JSONObject jsonObject = (JSONObject) new JSONObject(jsonStringToParse);
		ArrayList<BaseFilm> listOfFilmDetails = new ArrayList<>();
		JSONArray searchResults = new JSONArray();

		try {
			// Get list of jason objects within the Search section. This contains all the films found and the film
			// details.
			searchResults = jsonObject.getJSONArray("results");
		} catch (Exception error) {
			System.out.println("No Film Found");
		}

		for (int i = 0; i < searchResults.length(); i++) {
			String title = (String) searchResults.getJSONObject(i).get("original_title");
			String year = (String) searchResults.getJSONObject(i).get("release_date");

			// Because there is no Directory details listed indicate that as the name.
			BaseFilm baseFilm = new BaseFilm(title, year, "Director not listed");

			listOfFilmDetails.add(baseFilm);
		}

		return listOfFilmDetails;
	}
}

