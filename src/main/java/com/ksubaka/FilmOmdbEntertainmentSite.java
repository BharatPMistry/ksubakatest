package com.ksubaka;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.*;
import java.util.ArrayList;

public class FilmOmdbEntertainmentSite extends FilmEntertainmentSite {

	private final String URL_FILM_SITE = "https://www.omdbapi.com/";

	private final String SEARCH_PARAMETER = "s";
	private final String TITLE_PARAMETER = "t";

	public FilmOmdbEntertainmentSite() {
	}

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
			domain = new URL(URL_FILM_SITE);
			url = new URL(domain + "?" + SEARCH_PARAMETER + "=" + encodedFilmName + "&plot=full&r=json");
			url = EncodingTools.encodeUrl(url);
		} catch (MalformedURLException error) {
			error.printStackTrace();
		}

		return url;
	}

	private URL buildSearchTitleUrl(String searchFilmTitle) {
		URL domain = null;
		URL url = null;

		try {
			domain = new URL(URL_FILM_SITE);
			url = new URL(domain + "?" + TITLE_PARAMETER + "=" + searchFilmTitle + "&plot=full&r=json");
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
			searchResults = jsonObject.getJSONArray("Search");
		} catch (Exception error) {

			System.out.println("No Film Found");
		}

		// Build up the list of films with the Title, year of release and the director
		for (int i = 0; i < searchResults.length(); i++) {
			String title = (String) searchResults.getJSONObject(i).get("Title");
			String year = (String) searchResults.getJSONObject(i).get("Year");

			String director = getTheDirectorsNameUsingTheFilmTitle(title);

			BaseFilm baseFilm = new BaseFilm(title, year, director);

			listOfFilmDetails.add(baseFilm);
		}

		return listOfFilmDetails;
	}

	private String getTheDirectorsNameUsingTheFilmTitle(String filmTile) {
		URL searchUrl = buildSearchTitleUrl(filmTile);

		String jsonRresponseFromCall = RestAPICall.makeGetRestApiCallToService(searchUrl);

		String nameOfDirector = getDirectorNameFromJsonResponse(jsonRresponseFromCall);

		return nameOfDirector;
	}

	private String getDirectorNameFromJsonResponse(String jsonResponseToParse) {
		JSONObject jsonObject = (JSONObject) new JSONObject(jsonResponseToParse);

		String nameOfDirector = jsonObject.getString("Director");

		return nameOfDirector;
	}
}

