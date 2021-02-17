package com.example.entregable4.models.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class PeliculaOMDB {
	
	
	private String Title;
	
	private String imdbRating;

	private String Director;

	private String Year;
	
	private String Genre;
	
	private String Actors;
	
	private String Plot;
	
	private String Awards;
	
	private String Production;

	private String Runtime;

	private String Poster;

	public PeliculaOMDB() {
		super();
	}

	public PeliculaOMDB(String title, String imdbRating, String director, String year, String genre, String actors,
			String plot, String awards, String production, String runtime, String poster) {
		super();
		Title = title;
		this.imdbRating = imdbRating;
		Director = director;
		Year = year;
		Genre = genre;
		Actors = actors;
		Plot = plot;
		Awards = awards;
		Production = production;
		Runtime = runtime;
		Poster = poster;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String actors) {
		Actors = actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	public String getAwards() {
		return Awards;
	}

	public void setAwards(String awards) {
		Awards = awards;
	}

	public String getProduction() {
		return Production;
	}

	public void setProduction(String production) {
		Production = production;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		Runtime = runtime;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

	@Override
	public String toString() {
		return "PeliculaOMDB [Title=" + Title + ", imdbRating=" + imdbRating + ", Director=" + Director + ", Year="
				+ Year + ", Genre=" + Genre + ", Actors=" + Actors + ", Plot=" + Plot + ", Awards=" + Awards
				+ ", Production=" + Production + ", Runtime=" + Runtime + ", Poster=" + Poster + "]";
	}
	
	

}
