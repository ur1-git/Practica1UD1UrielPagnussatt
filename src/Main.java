import model.Content;
import model.CatalogModel;
import model.Movie;
import model.Series;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CatalogModel catalog = new CatalogModel();
        if (!catalog.getContents().isEmpty()) {
            throw new IllegalStateException("A new catalogue should be empty.");
        }

        catalog.addMovie(
                "Example Movie",
                4.99,
                LocalDate.of(2024, 6, 15),
                "A space adventure.",
                8,
                120
        );

        catalog.addSeries(
                "Example Series",
                12.99,
                LocalDate.of(2023, 9, 10),
                "A mystery to solve.",
                3,
                2,
                36
        );

        if (catalog.getContents().size() != 2) {
            throw new IllegalStateException("The catalogue should contain two entries.");
        }

        for (Content content : catalog.getContents()) {
            System.out.println(content);
            System.out.println("Price: " + content.getPrice());
            System.out.println("Release date: " + content.getReleaseDate());
        }

        Content first = catalog.getContents().get(0);
        Content second = catalog.getContents().get(1);
        if (!(first instanceof Movie) || !(second instanceof Series)) {
            throw new IllegalStateException("The content types were not stored correctly.");
        }

        Movie movie = (Movie) first;
        Series series = (Series) second;
        if (movie.getDurationMinutes() != 120 || series.getSeasonCount() != 2
                || series.getEpisodeCount() != 36) {
            throw new IllegalStateException("The specific content fields were not stored correctly.");
        }
        System.out.println("Duration: " + movie.getDurationMinutes());
        System.out.println("Seasons: " + series.getSeasonCount());
        System.out.println("Episodes: " + series.getEpisodeCount());
        System.out.println("Catalogue checks passed.");
    }

}
