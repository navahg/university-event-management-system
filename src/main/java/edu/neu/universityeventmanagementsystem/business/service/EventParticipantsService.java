package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.EventParticipantsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.EventParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EventParticipantsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class EventParticipantsService {

    private EventParticipantsRepository eventParticipantsRepository;

    @Autowired
    public EventParticipantsService(EventParticipantsRepository eventParticipantsRepository) {
        this.eventParticipantsRepository = eventParticipantsRepository;
    }

    public EventParticipantsEntity add(EventsEntity eventsEntity, UsersEntity usersEntity) {
        EventParticipantsEntity eventParticipantsEntity = new EventParticipantsEntity();

        eventParticipantsEntity.setUsersByIdUser(usersEntity);
        eventParticipantsEntity.setEventsByIdEvent(eventsEntity);

        return eventParticipantsRepository.save(eventParticipantsEntity);
    }

    public List<EventParticipantsEntity> findAllByEvent(EventsEntity event) {
        return eventParticipantsRepository.findAllByEvent(event);
    }

    public List<EventParticipantsEntity> findByUser(UsersEntity user) {
        return eventParticipantsRepository.findAllByUser(user);
    }
}