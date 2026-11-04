package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** Keeps the catalogue in memory and handles its XML files. */
public class CatalogModel {
    // arraylist of contents which can hold both movies and series
    private ArrayList<Content> contents;

    /** Starts with an empty catalogue. */
    public CatalogModel() {
        this.contents = new ArrayList<>();
    }

    /** @return the current entries in catalogue order */
    public ArrayList<Content> getContents() {
        return contents;
    }

    /** Creates a movie and adds it to the catalogue. */
    public void addMovie(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int durationMinutes) {
        Movie newMovie = new Movie(title, price, releaseDate, synopsis, valoration, durationMinutes);
        contents.add(newMovie);
    }

    /** Creates a series and adds it to the catalogue. */
    public void addSeries(String title, double price, LocalDate releaseDate, String synopsis, int valoration, int seasonCount, int episodeCount) {
        Series newSeries = new Series(title, price, releaseDate, synopsis, valoration, seasonCount, episodeCount);
        contents.add(newSeries);
    }

    /** Creates a documentary and adds it to the catalogue. */
    public void addDocumentary(String title, double price, LocalDate releaseDate, String synopsis,
                               int valoration, int durationMinutes, String topic) {
        contents.add(new Documentary(title, price, releaseDate, synopsis, valoration, durationMinutes, topic));
    }

    /**
     * Writes the catalogue to an XML file.
     * @param file destination chosen in the save dialog
     * @throws ParserConfigurationException if the XML builder cannot be created
     * @throws TransformerException if the XML cannot be written
     */
    public void exportXml(File file) throws ParserConfigurationException, TransformerException {
        // creates empty xml
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element catalogue = document.createElement("catalogue");
        document.appendChild(catalogue);

        for (Content content : contents) {
            // Each movie or series gets its own <entry> inside <catalogue>.
            Element entry = document.createElement("entry");
            catalogue.appendChild(entry);

            addXmlField(document, entry, "title", content.getTitle());
            addXmlField(document, entry, "price", String.valueOf(content.getPrice()));
            addXmlField(document, entry, "releaseDate", content.getReleaseDate().toString());
            addXmlField(document, entry, "synopsis", content.getSynopsis());
            addXmlField(document, entry, "rating", String.valueOf(content.getValoration()));

            if (content instanceof Movie) {
                Movie movie = (Movie) content;
                entry.setAttribute("type", "movie");
                addXmlField(document, entry, "duration", String.valueOf(movie.getDurationMinutes()));
            } else if (content instanceof Series) {
                Series series = (Series) content;
                entry.setAttribute("type", "series");
                addXmlField(document, entry, "seasons", String.valueOf(series.getSeasonCount()));
                addXmlField(document, entry, "episodes", String.valueOf(series.getEpisodeCount()));
            } else if (content instanceof Documentary) {
                Documentary documentary = (Documentary) content;
                entry.setAttribute("type", "documentary");
                addXmlField(document, entry, "duration", String.valueOf(documentary.getDurationMinutes()));
                addXmlField(document, entry, "topic", documentary.getTopic());
            }
        }

        // writes the XML to a file.
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(file));
    }

    private void addXmlField(Document document, Element entry, String name, String value) {
        Element field = document.createElement(name);
        field.setTextContent(value);
        entry.appendChild(field);
    }

    /**
     * Replaces the catalogue after all entries in the XML file have been read.
     * @param file source chosen in the open dialog
     * @throws ParserConfigurationException if the parser cannot be configured
     * @throws IOException if the file cannot be read or a catalogue field is invalid
     * @throws SAXException if the XML parser rejects the document
     */
    public void importXml(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // doctype rejection
        Document document = factory.newDocumentBuilder().parse(file);
        Element catalogue = document.getDocumentElement();
        if (!"catalogue".equals(catalogue.getTagName())) {
            throw new IOException("Expected a <catalogue> element.");
        }

        ArrayList<Content> imported = new ArrayList<>();
        NodeList entries = catalogue.getChildNodes();
        for (int i = 0; i < entries.getLength(); i++) {
            Node node = entries.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element entry = (Element) node;
                if (!"entry".equals(entry.getTagName())) {
                    throw new IOException("Expected an <entry> element inside <catalogue>.");
                }
                imported.add(readEntry(entry));
            }
        }
        contents.clear();
        contents.addAll(imported);
    }

    private Content readEntry(Element entry) throws IOException {
        String title = readXmlField(entry, "title");
        String synopsis = readXmlField(entry, "synopsis");
        String type = entry.getAttribute("type");

        try {
            double price = Double.parseDouble(readXmlField(entry, "price"));
            LocalDate date = LocalDate.parse(readXmlField(entry, "releaseDate"));
            int rating = Integer.parseInt(readXmlField(entry, "rating"));
            if ("movie".equals(type)) {
                int duration = Integer.parseInt(readXmlField(entry, "duration"));
                return new Movie(title, price, date, synopsis, rating, duration);
            }
            if ("series".equals(type)) {
                int seasons = Integer.parseInt(readXmlField(entry, "seasons"));
                int episodes = Integer.parseInt(readXmlField(entry, "episodes"));
                return new Series(title, price, date, synopsis, rating, seasons, episodes);
            }
            if ("documentary".equals(type)) {
                int duration = Integer.parseInt(readXmlField(entry, "duration"));
                String topic = readXmlField(entry, "topic");
                return new Documentary(title, price, date, synopsis, rating, duration, topic);
            }
            throw new IOException("Unknown entry type: " + type);
        } catch (NumberFormatException | DateTimeParseException ex) {
            throw new IOException("Invalid number or date in entry: " + title, ex);
        }
    }

    private String readXmlField(Element entry, String name) throws IOException {
        Node field = entry.getElementsByTagName(name).item(0);
        if (field == null) {
            throw new IOException("Missing XML field: " + name);
        }
        return field.getTextContent();
    }
}
