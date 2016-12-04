package com.ksubaka;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FilmTmdbEntertainmentSiteTest extends TestCase {

	@Test
	public void testGetFilmEntertainmentSiteResults() throws Exception {
		FilmTmdbEntertainmentSite searchPublicApiForItem = new FilmTmdbEntertainmentSite();

		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getFilmEntertainmentSiteResults("Indiana Jones");

		Assert.assertTrue(!listOfFilms.isEmpty());

		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Last Crusade", "1989-05-24");
		checkFilmExistsInList(listOfFilms, "Indiana Jones 5", "2019-07-19");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Temple of Doom", "1984-05-23");
		checkFilmExistsInList(listOfFilms, "Indiana Jones: Making the Trilogy", "2003-10-21");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Kingdom of the Crystal Skull", "2008-05-21");
		checkFilmExistsInList(listOfFilms, "Raiders of the Lost Ark", "1981-06-12");
		checkFilmExistsInList(listOfFilms, "The Adventures of Indiana Jones", "2016-09-29");
		checkFilmExistsInList(listOfFilms, "Indiana Jones and the Ultimate Quest", "2008-05-18");
		checkFilmExistsInList(listOfFilms, "Crocodile Jones: The Son of Indiana Dundee", "1990-04-10");
	}

	@Test
	public void testGetFilmEntertainmentSiteResultsForAFrenchFilm() throws Exception {
		FilmTmdbEntertainmentSite searchPublicApiForItem = new FilmTmdbEntertainmentSite();

		// This is also to test characters other then the normal English characters
		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getFilmEntertainmentSiteResults("Les B�tises");

		Assert.assertTrue("list not found to be empty size is : " + listOfFilms.size(), listOfFilms.isEmpty());
	}

	private void checkFilmExistsInList(ArrayList<BaseFilm> listOfFilms, String nameOfFilm, String yearReleased) {
		boolean found = false;
		String filmName = null;
		String year = null;

		for (BaseFilm film : listOfFilms) {
			filmName = film.getFilmName();
			year = film.getReleasedYear();

			if (filmName.compareToIgnoreCase(nameOfFilm) == 0 && year.compareToIgnoreCase(yearReleased) == 0) {
				found = true;
				break;
			}
		}

		Assert.assertTrue("Data for file " + nameOfFilm + " does not match ", found);
	}
}