package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import javax.swing.*;
import java.awt.*;
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

}
