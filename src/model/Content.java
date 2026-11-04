package model;

import java.time.LocalDate;

/** The fields shared by all catalogue entries. */
public abstract class Content {
    private String title;
    private double price;
    private LocalDate releaseDate;
    private String synopsis;
    private int valoration;

    /** Stores the common fields of an entry. */
    public Content(String title, double price, LocalDate releaseDate, String synopsis, int valoration) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.valoration = valoration;
    }

    /** @return the title shown in the catalogue */
    public String getTitle() {
        return title;
    }

    /** @return the entry price */
    public double getPrice() {
        return price;
    }

    /** @return the original release date */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /** @return the description */
    public String getSynopsis() {
        return synopsis;
    }

    /** @return the rating */
    public int getValoration() {
        return valoration;
    }

    @Override
    public String toString() {
        return title;
    }
}
