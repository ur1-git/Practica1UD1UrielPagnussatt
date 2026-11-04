package util;

import javax.swing.JOptionPane;
import java.awt.Component;

/** Dialogs shared by the application. */
public class Util {
    /**
     * Asks the user to confirm before closing.
     * @param parent the catalogue window
     * @return true when the user chooses Yes
     */
    public static boolean confirmClose(Component parent) {
        int result = JOptionPane.showConfirmDialog(parent, "Close the catalogue?",
                "Confirm exit", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
