package modelo;

import java.time.LocalDate;

public class Series extends Content {
    private int seasonCount;
    private int episodeCount;
    // constructor


    public Series(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int seasonCount, int episodeCount) {
        super(title, price, releaseDate, synopsis, valoration);
        this.seasonCount = seasonCount;
        this.episodeCount = episodeCount;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    @Override
    public String toString() {
        return "Series: " + getTitle();
    }
}
