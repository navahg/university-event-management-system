package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

/**
 * FormController class, an abstract class with necessary methods for any form controller
 *
 * @author ragha <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/11/2018
 */
public abstract class FormController {

    public abstract void prepareAndOpenForm();

    protected void registerAction(JButton button, ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    protected void registerAction(JComboBox comboBox, ActionListener actionListener) {
        comboBox.addActionListener(actionListener);
    }

    protected void registerAction(JList list, ListSelectionListener listSelectionListener) {
        list.addListSelectionListener(listSelectionListener);
    }

}
