package com.ksubaka;

import java.util.ArrayList;

public class SearchPublicApiForItem {

	public SearchPublicApiForItem() {
	}

	public ArrayList<BaseFilm> getSearchResultForFilms(String publicWebSiteApi, String searchItem) {

		ArrayList<BaseFilm> listOfFilms = null;
		FilmEntertainmentSite filmEntertainmentsite = null;

		if (publicWebSiteApi.compareToIgnoreCase("omdb") == 0) {
			filmEntertainmentsite = new FilmOmdbEntertainmentSite();
		}

		if (publicWebSiteApi.compareToIgnoreCase("tmdb") == 0) {
			filmEntertainmentsite = new FilmTmdbEntertainmentSite();
		}

		if (filmEntertainmentsite != null) {
			listOfFilms = filmEntertainmentsite.getFilmEntertainmentSiteResults(searchItem);
		} else {
			System.out.println("Public API not known.  The supported API's are ombd and tmdb");
			listOfFilms = new ArrayList(); // create an empty list to return
		}

		return listOfFilms;
	}
}
