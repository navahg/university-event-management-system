package edu.neu.universityeventmanagementsystem.business.util;

import java.nio.file.Paths;

/**
 * Interface containing constant paths for the application.
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @since Apr 10, 2018
 * @version 1.0
 */
public interface ConstantPaths {

    interface Resources {
        String IMAGES_DIR = Paths.get(System.getProperty("user.dir"),
                "src", "main", "resources", "images").toString();
    }
}
