package model;

import java.time.LocalDate;

/** A movie with a running time. */
public class Movie extends Content {
    private int durationMinutes;
    /** Creates a movie from its catalogue fields. */
    public Movie(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int durationMinutes) {
        super(title, price, releaseDate, synopsis, valoration);
        this.durationMinutes = durationMinutes;
    }

    /** @return the running time in minutes */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return "Movie: " + getTitle();
    }
}
