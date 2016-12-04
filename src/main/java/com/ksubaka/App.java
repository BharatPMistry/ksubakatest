package com.ksubaka;

import java.util.ArrayList;

//	Example usage : java -Dapi=imdb -Dmovie=Indiana Jones query.jar .
//	Expected output : the list of all 4 Indiana Jones movies, with year + director, on 4 lines
public class App {
	public static void main(String[] args) {

		String publicApiToBeUsed = System.getProperty("api");
		String detailsOfMovieToSearch = System.getProperty("movie");

		if (isThePublicApiAndMovieParametersValid(publicApiToBeUsed, detailsOfMovieToSearch)) {
			SearchPublicApiForItem searchPublicApiForItem = new SearchPublicApiForItem();

			ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getSearchResultForFilms(publicApiToBeUsed, detailsOfMovieToSearch);

			displayTheDetailsOfAllTheFilmsRetrievedFromThePublicApi(listOfFilms);

		} else {
			System.out.println("Usage is : java -Dapi=[omdb|tmdb] -Dmovie=movename -cp target/ksubakatest-1.0-SNAPSHOT-jar-with-dependencies.jar com.ksubaka.App");
		}
	}

	private static boolean isThePublicApiAndMovieParametersValid(String apiToBeUsed, String detailsOfMovieToSearch) {
		return apiToBeUsed != null && detailsOfMovieToSearch != null && !apiToBeUsed.isEmpty() && !detailsOfMovieToSearch.isEmpty();
	}

	private static void displayTheDetailsOfAllTheFilmsRetrievedFromThePublicApi(ArrayList<BaseFilm> listOfFilms) {
		System.out.println();
		for (BaseFilm film : listOfFilms) {
			System.out.println("Film: " + film.getFilmName());
			System.out.println("Release Date: " + film.getReleasedYear());
			System.out.println("Director: " + film.getDirector());
			System.out.println();
		}
	}
}
