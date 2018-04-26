package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

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
        for (ActionListener listener : button.getActionListeners()) {
            button.removeActionListener(listener);
        }

        button.addActionListener(actionListener);
    }

    protected void registerAction(JComboBox comboBox, ActionListener actionListener) {
        for (ActionListener listener : comboBox.getActionListeners()) {
            comboBox.removeActionListener(listener);
        }

        comboBox.addActionListener(actionListener);
    }

    protected void registerAction(JCheckBox checkBox, ActionListener actionListener) {
        for (ActionListener listener : checkBox.getActionListeners()) {
            checkBox.removeActionListener(listener);
        }

        checkBox.addActionListener(actionListener);
    }

    protected void registerListSelection(JList list, ListSelectionListener listSelectionListener) {
        for (ListSelectionListener listener : list.getListSelectionListeners()) {
            list.removeListSelectionListener(listener);
        }

        list.addListSelectionListener(listSelectionListener);
    }

    protected void registerMouseClick(JComponent component, Consumer<MouseEvent> listener) {
        for (MouseListener mouseListener : component.getMouseListeners()) {
            component.removeMouseListener(mouseListener);
        }

        component.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.accept(e);
            }
        });
    }

}
