package view;

import com.github.lgooddatepicker.components.DatePicker;
import model.Content;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.*;

/** The Swing form and window for browsing and adding entries. */
public class CatalogView {
    private JPanel mainPanel;
    public JTextField titleField;
    public JTextField priceField;
    public JTextField ratingField;
    public JTextField durationField;
    public JTextField seasonsField;
    public JTextField episodesField;
    public JRadioButton movieButton;
    public JRadioButton seriesButton;
    public JRadioButton documentaryButton;
    public JTextField topicField;
    public JLabel topicLabel;
    public JLabel durationLabel;
    public JLabel seasonsLabel;
    public JLabel episodesLabel;
    public JTextArea synopsisArea;
    public DatePicker releaseDatePicker;
    public JComboBox<Content> contentComboBox;
    public JButton newButton;
    public JButton importButton;
    public JButton exportButton;
    public JButton closeButton;

    public JFrame frame;

    /** Builds the application window around the IntelliJ form. */
    public CatalogView() {
        frame = new JFrame("Filminator");
        URL icon = CatalogView.class.getResource("/icons/filminator.png");
        if (icon != null) {
            frame.setIconImage(new ImageIcon(icon).getImage());
        }
        applyCatalogueFont();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void applyCatalogueFont() {
        // The font is a resource inside the app, so users do not need to install it.
        try (InputStream file = CatalogView.class.getResourceAsStream("/fonts/JetBrainsMono-Regular.ttf")) {
            if (file != null) {
                Font font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(13f);
                applyFont(mainPanel, font);
            }
        } catch (FontFormatException | IOException ex) {
            System.err.println("Could not load JetBrains Mono: " + ex.getMessage());
        }
    }

    private void applyFont(Component component, Font font) {
        component.setFont(font);
        // Panels and scroll panes contain other components that also need the font.
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyFont(child, font);
            }
        }
    }
}
