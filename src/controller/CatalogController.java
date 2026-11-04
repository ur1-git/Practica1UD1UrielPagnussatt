package controller;

import model.CatalogModel;
import model.Content;
import model.Documentary;
import model.Movie;
import model.Series;
import util.Util;
import org.xml.sax.SAXException;
import view.CatalogView;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/** Connects the catalogue form to the model and handles user actions. */
public class CatalogController implements ActionListener {
    private CatalogView view;
    private CatalogModel model;

    /**
     * Registers the controller with the catalogue window.
     * @param view the form and window
     * @param model the catalogue to display and change
     */
    public CatalogController(CatalogView view, CatalogModel model) {
        this.view = view;
        this.model = model;
        view.newButton.addActionListener(this);
        view.exportButton.addActionListener(this);
        view.importButton.addActionListener(this);
        view.closeButton.addActionListener(this);
        view.movieButton.addActionListener(this);
        view.seriesButton.addActionListener(this);
        view.documentaryButton.addActionListener(this);
        view.contentComboBox.addActionListener(this);

        // The window X follows the same rule as the Close button.
        view.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmClose();
            }
        });
        updateTypeFields();
        view.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.newButton) {
            addContentFromForm();
        } else if (e.getSource() == view.exportButton) {
            chooseExportFile();
        } else if (e.getSource() == view.importButton) {
            chooseImportFile();
        } else if (e.getSource() == view.closeButton) {
            confirmClose();
        } else if (e.getSource() == view.contentComboBox) {
            showSelectedContent();
        } else if (e.getSource() == view.movieButton || e.getSource() == view.seriesButton
                || e.getSource() == view.documentaryButton) {
            updateTypeFields();
        }
    }

    private void addContentFromForm() {
        String title = view.titleField.getText().trim();
        String synopsis = view.synopsisArea.getText().trim();
        LocalDate date = view.releaseDatePicker.getDate();

        if (title.isEmpty() || date == null) {
            JOptionPane.showMessageDialog(view.frame, "Enter a title and release date.");
            return;
        }
        try {
            Double price = Double.parseDouble(view.priceField.getText().trim());
            int rating = Integer.parseInt(view.ratingField.getText().trim());
            if (view.movieButton.isSelected()) {
                int duration = Integer.parseInt(view.durationField.getText().trim());
                model.addMovie(title, price, date, synopsis, rating, duration);
            } else if (view.documentaryButton.isSelected()) {
                int duration = Integer.parseInt(view.durationField.getText().trim());
                String topic = view.topicField.getText().trim();
                if (topic.isEmpty()) {
                    JOptionPane.showMessageDialog(view.frame, "Enter a documentary topic.");
                    return;
                }
                model.addDocumentary(title, price, date, synopsis, rating, duration, topic);
            } else {
                int seasons = Integer.parseInt(view.seasonsField.getText().trim());
                int episodes = Integer.parseInt(view.episodesField.getText().trim());
                model.addSeries(title, price, date, synopsis, rating, seasons, episodes);
            }

            refreshCatalogue();
            clearForm();
            Toolkit.getDefaultToolkit().beep();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view.frame,
                    "Enter a price like 4.99 and whole numbers for rating and the selected type's fields.");
        }
    }


    private void chooseExportFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(view.frame); // showSaveDialog opens the dialog

        if (result == JFileChooser.APPROVE_OPTION) { // prevents saving if the user didnt approve
            File file = chooser.getSelectedFile();
            try {
                model.exportXml(file);
                Toolkit.getDefaultToolkit().beep();
            } catch (ParserConfigurationException | TransformerException ex) {
                JOptionPane.showMessageDialog(view.frame, "Could not export the catalogue: " + ex.getMessage());
            }
        }
    }


    private void chooseImportFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(view.frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                model.importXml(chooser.getSelectedFile());
                refreshCatalogue();
                clearForm();
                Toolkit.getDefaultToolkit().beep();
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                JOptionPane.showMessageDialog(view.frame, "Could not import the catalogue: " + ex.getMessage());
            }
        }
    }

    private void confirmClose() {
        if (Util.confirmClose(view.frame)) {
            view.frame.dispose();
        }
    }

    private void refreshCatalogue() {
        // Prepare the new choices with no selection, so the form stays clear.
        DefaultComboBoxModel<Content> choices = new DefaultComboBoxModel<>();
        for (Content content : model.getContents()) {
            choices.addElement(content);
        }
        choices.setSelectedItem(null);
        view.contentComboBox.setModel(choices);
    }

    private void updateTypeFields() {
        boolean series = view.seriesButton.isSelected();
        boolean documentary = view.documentaryButton.isSelected();

        view.durationLabel.setVisible(!series);
        view.durationField.setVisible(!series);
        view.seasonsLabel.setVisible(series);
        view.seasonsField.setVisible(series);
        view.episodesLabel.setVisible(series);
        view.episodesField.setVisible(series);
        view.topicLabel.setVisible(documentary);
        view.topicField.setVisible(documentary);
    }

    private void showSelectedContent() {
        Content content = (Content) view.contentComboBox.getSelectedItem();
        if (content == null) {
            return;
        }

        clearForm();
        view.titleField.setText(content.getTitle());
        view.priceField.setText(String.valueOf(content.getPrice()));
        view.releaseDatePicker.setDate(content.getReleaseDate());
        view.synopsisArea.setText(content.getSynopsis());
        view.ratingField.setText(String.valueOf(content.getValoration()));

        if (content instanceof Movie) {
            Movie movie = (Movie) content;
            view.movieButton.setSelected(true);
            view.durationField.setText(String.valueOf(movie.getDurationMinutes()));
        } else if (content instanceof Series) {
            Series series = (Series) content;
            view.seriesButton.setSelected(true);
            view.seasonsField.setText(String.valueOf(series.getSeasonCount()));
            view.episodesField.setText(String.valueOf(series.getEpisodeCount()));
        } else if (content instanceof Documentary) {
            Documentary documentary = (Documentary) content;
            view.documentaryButton.setSelected(true);
            view.durationField.setText(String.valueOf(documentary.getDurationMinutes()));
            view.topicField.setText(documentary.getTopic());
        }
        updateTypeFields();
    }

    private void clearForm() {
        view.titleField.setText("");
        view.priceField.setText("");
        view.ratingField.setText("");
        view.synopsisArea.setText("");
        view.durationField.setText("");
        view.seasonsField.setText("");
        view.episodesField.setText("");
        view.topicField.setText("");

        view.releaseDatePicker.setDate(null); // date picker doesnt use string
    }
}
