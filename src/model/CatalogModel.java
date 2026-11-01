package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CatalogModel {
    // arraylist of contents which can hold both movies and series
    private ArrayList<Content> contents;

    // constructor with the arraylist
    public CatalogModel() {
        this.contents = new ArrayList<>();
    }

    // Returns the catalogue list, containing movies and series.
    public ArrayList<Content> getContents() {
        return contents;
    }

    // addMovie method that contents.add(movie) with the movie constructor
    public void addMovie(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int durationMinutes) {
        Movie newMovie = new Movie(title, price, releaseDate, synopsis, valoration, durationMinutes);
        contents.add(newMovie);
    }

    // same for series
    public void addSeries(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int seasonCount, int episodeCount) {
        Series newSeries = new Series(title, price, releaseDate, synopsis, valoration, seasonCount, episodeCount);
        contents.add(newSeries);
    }
}
