package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.NotificationsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * NotificationsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class NotificationsService {

    private NotificationsRepository notificationsRepository;

    @Autowired
    public NotificationsService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public NotificationsEntity create() {
        return new NotificationsEntity();
    }

    public NotificationsEntity save(NotificationsEntity notification) {
        return notificationsRepository.save(notification);
    }

    public List<NotificationsEntity> findAllByIdUser(UsersEntity user) {
        return notificationsRepository.findAllByIdUser(user);
    }

    @Transactional
    public void delete(NotificationsEntity notificationsEntity) {
        notificationsRepository.delete(notificationsEntity);
    }
}