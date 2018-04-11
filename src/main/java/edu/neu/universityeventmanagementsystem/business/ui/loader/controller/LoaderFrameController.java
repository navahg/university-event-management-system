package edu.neu.universityeventmanagementsystem.business.ui.loader.controller;

import edu.neu.universityeventmanagementsystem.business.ui.loader.view.LoaderFrameView;

/**
 * MainFrameController class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/6/18
 */
public class LoaderFrameController {

    private LoaderFrameView loaderFrameView;

    public LoaderFrameController(LoaderFrameView loaderFrameView) {
        this.loaderFrameView = loaderFrameView;
    }

    public void prepareAndOpenFrame() {
        loaderFrameView.setVisible(true);
    }

    public void closeFrame () {
        loaderFrameView.setVisible(false);
    }
}
