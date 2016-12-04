package com.ksubaka;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SearchPublicApiForItemTest extends TestCase {

	@Test
	public void testOmdbFilmSearch() {
		SearchPublicApiForItem searchPublicApiForItem = new SearchPublicApiForItem();

		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getSearchResultForFilms("omdb", "Indiana Jones");

		Assert.assertTrue(!listOfFilms.isEmpty());
	}

	@Test
	public void testTmdbFilmSearch() {
		SearchPublicApiForItem searchPublicApiForItem = new SearchPublicApiForItem();

		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getSearchResultForFilms("tmdb", "Indiana Jones");

		Assert.assertTrue(!listOfFilms.isEmpty());
	}

	@Test
	public void testForUnknownApiFilmSearch() {
		SearchPublicApiForItem searchPublicApiForItem = new SearchPublicApiForItem();

		ArrayList<BaseFilm> listOfFilms = searchPublicApiForItem.getSearchResultForFilms("xxx", "Indiana Jones");

		Assert.assertTrue(listOfFilms.isEmpty());
	}
}