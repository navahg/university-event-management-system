package edu.neu.universityeventmanagementsystem.business.util;

/**
 * LookAndFeelUtils class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/10/18
 */
public class LookAndFeelUtils {

    public static void setSystemLookAndFeel() {
        /* Set the look and feel of the system */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LookAndFeelUtils.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
