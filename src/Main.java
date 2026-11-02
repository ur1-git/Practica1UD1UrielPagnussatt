import view.CatalogView;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        // Choose the appearance before creating any Swing components.
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("GTK unavailable; using the default Swing appearance.");
        }
        CatalogView view = new CatalogView();
    }

}
