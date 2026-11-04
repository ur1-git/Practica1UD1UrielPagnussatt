package model;

import java.time.LocalDate;

/** A series with season and episode counts. */
public class Series extends Content {
    private int seasonCount;
    private int episodeCount;
    /** Creates a series from its catalogue fields. */
    public Series(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int seasonCount, int episodeCount) {
        super(title, price, releaseDate, synopsis, valoration);
        this.seasonCount = seasonCount;
        this.episodeCount = episodeCount;
    }

    /** @return the number of seasons */
    public int getSeasonCount() {
        return seasonCount;
    }

    /** @return the number of episodes */
    public int getEpisodeCount() {
        return episodeCount;
    }

    @Override
    public String toString() {
        return "Series: " + getTitle();
    }
}
