package view;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class CatalogView {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField priceField;
    private JTextField ratingField;
    private JTextField durationField;
    private JTextField seasonsField;
    private JTextField episodesField;
    private JRadioButton movieButton;
    private JRadioButton seriesButton;
    private JTextArea synopsisArea;
    private DatePicker releaseDatePicker;
    private JComboBox contentComboBox;
    private JButton newButton;
    private JButton importButton;
    private JButton exportButton;
    private JButton closeButton;

    public JFrame frame;

    public CatalogView() {
        frame = new JFrame("Audiovisual Catalogue");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
