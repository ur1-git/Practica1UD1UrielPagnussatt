import controller.CatalogController;
import model.CatalogModel;
import view.CatalogView;

import javax.swing.*;


/** Starts the Filminator catalogue application. */
public class Main {
    /**
     * Sets the Swing appearance and connects the window to the catalogue.
     * @param args command line arguments, currently unused
     */
    public static void main(String[] args) {
        // Choose the appearance before creating any Swing components.
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("GTK unavailable; using the default Swing appearance.");
        }
        CatalogController controller = new CatalogController(
                new CatalogView(),
                new CatalogModel()
        );
    }

}
