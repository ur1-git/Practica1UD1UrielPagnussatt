package view;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

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
    public JTextArea synopsisArea;
    public DatePicker releaseDatePicker;
    public JComboBox contentComboBox;
    public JButton newButton;
    public JButton importButton;
    public JButton exportButton;
    public JButton closeButton;

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
