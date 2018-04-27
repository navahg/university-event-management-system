package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.entity.NotificationsEntity;
import edu.neu.universityeventmanagementsystem.business.service.NotificationsService;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.NotificationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

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

    private NotificationsService notificationsService;
    private NotificationView notificationView;
    private NotificationsEntity notification;
    private ApplicationContext context;

    @Autowired
    public NotificationController(NotificationsService notificationsService,
                                  NotificationView notificationView,
                                  ApplicationContext context) {
        this.notificationsService = notificationsService;
        this.notificationView = notificationView;
        this.context = context;
        notification = null;
    }

    @Override
    public void prepareAndOpenForm() {
        if (notification != null) {
            notificationView = context.getBean(NotificationView.class, notification);
            makeNotificationRead(notification);
            registerEvents(notificationView);
        } else {
            notificationView = context.getBean(NotificationView.class);
        }
    }

    private void makeNotificationRead(NotificationsEntity notification) {
        notification.setReadFlag(true);
        notificationsService.save(notification);
    }

    private void registerEvents(NotificationView notificationView) {
        registerMouseClick(notificationView.getDeleteButton(), this::deleteNotification);
    }

    private void deleteNotification(MouseEvent event) {
        if (!(event.getSource() instanceof javax.swing.JLabel))
            return;

        if (!(((javax.swing.JLabel) event.getSource()).getParent().getParent() instanceof NotificationView))
            return;
        NotificationView selectedNotification = (NotificationView) ((javax.swing.JLabel) event.getSource()).getParent().getParent();

        notificationsService.delete(selectedNotification.getNotification());

        JPanel parent = (JPanel) selectedNotification.getParent();
        parent.remove(selectedNotification);
        parent.revalidate();
        parent.repaint();
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
