package model;

import java.time.LocalDate;

/** A documentary with a running time and a topic. */
public class Documentary extends Content {
    private int durationMinutes;
    private String topic;

    /** Creates a documentary from its catalogue fields. */
    public Documentary(String title, double price, LocalDate releaseDate, String synopsis,
                       int valoration, int durationMinutes, String topic) {
        super(title, price, releaseDate, synopsis, valoration);
        this.durationMinutes = durationMinutes;
        this.topic = topic;
    }

    /** @return the running time in minutes */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /** @return the documentary's subject */
    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "Documentary: " + getTitle();
    }
}
