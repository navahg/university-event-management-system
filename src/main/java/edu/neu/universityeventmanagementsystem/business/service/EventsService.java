package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.EventStatusEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EventsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class EventsService {

    private EventsRepository eventsRepository;

    @Autowired
    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public EventsEntity create() {
        return new EventsEntity();
    }

    public EventsEntity save(EventsEntity newEvent) {
        return eventsRepository.save(newEvent);
    }

    public List<EventsEntity> findAll() {
        return eventsRepository.findAll();
    }

    public List<EventsEntity> findAllByCreator(UsersEntity creator) {
        return eventsRepository.findAllByIdCreator(creator);
    }

    public List<EventsEntity> findAllByEventStatus(EventStatusEntity status) {
        return eventsRepository.findAllByEventStatus(status);
    }

    public List<EventsEntity> findAllByHierarchyAndIdEntity(HierarchyEntity hierarchy, Integer idEntity) {
        return eventsRepository.findAllByHierarchyAndIdEntity(hierarchy, idEntity);
    }
}