/*
 * Copyright (C) 2018 Raghavan Renganathan <renganathan.raghavan@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.neu.universityeventmanagementsystem.business.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A utility class for handling Image files
 *
 * @author Raghavan Renganathan <renganathan.raghavan@gmail.com>
 * @version 1.0
 * @since Feb 9, 2018
 */
public class ImageTools {

    private final static String IMAGES_DIR = ConstantPaths.Resources.IMAGES_DIR + "/";

    /**
     * This method sets icon for the JComponents.
     *
     * @param component A JComponent for which the icon has to be set.
     *                  <b>Should be a JButton or JLabel</b>
     * @param filename  The filename of the icon.
     *                  <b>Should be located in /resources/images/ directory</b>
     * @param width     Desired width of the icon
     * @param height    Desired height of the icon
     */
    public static void setIcon(JComponent component, String filename,
                               int width, int height) {
        if (!(component instanceof JButton) && !(component instanceof JLabel))
            return;

        JButton _button;
        JLabel _label;

        File f;
        BufferedImage image;
        Image scaledImage;
        try {
            f = new File(IMAGES_DIR + filename);
            image = ImageIO.read(f);
            scaledImage = image.getScaledInstance(width, height,
                    Image.SCALE_FAST);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error rendering Picture!");
            return;
        }

        if (component instanceof JButton) {
            _button = (JButton) component;
            _button.setIcon(new ImageIcon(scaledImage));
        } else {
            _label = (JLabel) component;
            _label.setIcon(new ImageIcon(scaledImage));
        }
    }

    /**
     * The method to generate an Image object from a file.
     *
     * @param filename The filename of the icon.
     *                 <b>Should be located in /resources/images/ directory</b>
     * @param width    Desired width of the image
     * @param height   Desired height of the image
     * @return The Image object of the desired image file
     */
    public static Image loadImage(String filename, int width, int height) {
        File f;
        BufferedImage image;
        Image scaledImage;
        try {
            f = new File(IMAGES_DIR + filename);
            image = ImageIO.read(f);
            scaledImage = image.getScaledInstance(width, height,
                    Image.SCALE_FAST);
            return scaledImage;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error rendering Picture!");
            return null;
        }
    }
}
