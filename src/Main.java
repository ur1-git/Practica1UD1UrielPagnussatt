import modelo.Content;
import modelo.Movie;
import modelo.Series;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie(
                "Example Movie",
                4.99,
                LocalDate.of(2024, 6, 15),
                "A space adventure.",
                120,
                93
        );

        Series series = new Series(
                "Example Series",
                12.99,
                LocalDate.of(2023, 9, 10),
                "A mystery to solve.",
                3,
                23,
                36
        );

        Content[] catalog = {movie, series};

        for (Content content : catalog) {
            System.out.println(content);
            System.out.println("Price: " + content.getPrice());
            System.out.println("Release date: " + content.getReleaseDate());
        }

        System.out.println("Duration: " + movie.getDurationMinutes());
        System.out.println("Seasons: " + series.getSeasonCount());
    }

}
