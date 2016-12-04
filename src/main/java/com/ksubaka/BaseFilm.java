package com.ksubaka;

public class BaseFilm implements BaseItem {

	private String director;
	private String filmName;
	private String releasedYear;

	public BaseFilm(String filmName, String year, String director) {
		this.filmName = filmName;
		this.releasedYear = year;
		this.director = director;
	}

	@Override
	public String getFilmName() {
		return filmName;
	}

	@Override
	public String getReleasedYear() {
		return releasedYear;
	}

	@Override
	public String getDirector() {
		return director;
	}

	public void setReleasedYear(String releasedYear) {
		this.releasedYear = releasedYear;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

}
