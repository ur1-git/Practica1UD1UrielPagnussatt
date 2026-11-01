package model;

import java.time.LocalDate;

public abstract class Content {
    private String title;
    private double price;
    private LocalDate releaseDate;
    private String synopsis;
    private int valoration;

    // constructor
    public Content(String title, double price, LocalDate releaseDate, String synopsis, int valoration) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.valoration = valoration;
    }

    // getter for every variable
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getValoration() {
        return valoration;
    }

    @Override
    public String toString() {
        return title;
    }
}
