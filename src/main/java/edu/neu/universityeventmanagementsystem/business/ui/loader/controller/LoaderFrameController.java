package edu.neu.universityeventmanagementsystem.business.ui.loader.controller;

import edu.neu.universityeventmanagementsystem.business.ui.loader.view.LoaderFrameView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;

/**
 * MainFrameController class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/6/18
 */
public final class LoaderFrameController extends FormController {

    private LoaderFrameView loaderFrameView;

    public LoaderFrameController(LoaderFrameView loaderFrameView) {
        this.loaderFrameView = loaderFrameView;
    }

    @Override
    public void prepareAndOpenForm() {
        loaderFrameView.setVisible(true);
    }

    public void closeFrame() {
        loaderFrameView.setVisible(false);
    }
}
