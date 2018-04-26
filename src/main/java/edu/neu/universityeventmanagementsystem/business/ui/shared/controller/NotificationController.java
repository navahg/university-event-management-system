package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.entity.NotificationsEntity;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.NotificationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * NotificationController class
 *
 * @author  Raghavan Renganathan <renganathan.raghavan@gmail.com> <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 26, 2018
 */
@Controller
@Lazy
public class NotificationController extends FormController implements InnerViewController {

    private NotificationView notificationView;
    private NotificationsEntity notification;
    private ApplicationContext context;

    @Autowired
    public NotificationController(NotificationView notificationView,
                                  ApplicationContext context) {
        this.notificationView = notificationView;
        this.context = context;
        notification = null;
    }

    @Override
    public void prepareAndOpenForm() {
        if (notification != null) {
            notificationView = context.getBean(NotificationView.class, notification);
        } else {
            notificationView = context.getBean(NotificationView.class);
        }
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return notificationView;
    }

    public Component getView(NotificationsEntity notification) {
        this.notification = notification;
        return getView();
    }
}
