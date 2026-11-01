package model;

import java.time.LocalDate;

public class Movie extends Content {
    private int durationMinutes;
    // constructor


    public Movie(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int durationMinutes) {
        super(title, price, releaseDate, synopsis, valoration);
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return "Movie: " + getTitle();
    }
}
