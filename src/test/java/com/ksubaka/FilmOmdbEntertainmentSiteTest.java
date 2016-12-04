package com.ksubaka;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FilmOmdbEntertainmentSiteTest extends TestCase {

	@Test
	public void testGetFilmEntertainmentSiteResults() throws Exception {
		FilmOmdbEntertainmentSite searchPublicApiForItem = new FilmOmdbEntertainmentSite();

		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getFilmEntertainmentSiteResults("Indiana Jones");

		Assert.assertTrue(!listOfFilms.isEmpty());

		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Last Crusade", "1989", "Steven Spielberg");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Kingdom of the Crystal Skull", "2008", "Steven Spielberg");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Temple of Doom", "1984", "Steven Spielberg");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Fate of Atlantis", "1992", "Hal Barwood");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Temple of the Forbidden Eye", "1995", "Gregory Marquette");
	}

	private void checkFilmExistsInList(ArrayList<BaseFilm> listOfFilms, String nameOfFilm, String yearReleased, String filmDirector) {
		boolean found = false;
		String filmName = null;
		String year = null;
		String director = null;

		for (BaseFilm film : listOfFilms) {
			filmName = film.getFilmName();
			year = film.getReleasedYear();
			director = film.getDirector();

			if (filmName.compareToIgnoreCase(nameOfFilm) == 0 && year.compareToIgnoreCase(yearReleased) == 0 && director.compareToIgnoreCase(filmDirector) == 0) {
				found = true;
				break;
			}
		}

		Assert.assertTrue("Data for file " + nameOfFilm + " does not match ", found);
	}

	@Test
	public void testGetFilmEntertainmentSiteResultsForAFrenchFilm() throws Exception {
		FilmOmdbEntertainmentSite searchPublicApiForItem = new FilmOmdbEntertainmentSite();

		// This is also to test characters other then the normal English characters
		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getFilmEntertainmentSiteResults("Les Bêtises");

		Assert.assertTrue("list not found to be empty size is : " + listOfFilms.size(), listOfFilms.isEmpty());
	}
}