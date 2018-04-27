package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.EventStatusEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.EventsRepository;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private EventStatusService eventStatusService;

    @Autowired
    public EventsService(EventsRepository eventsRepository, EventStatusService eventStatusService) {
        this.eventsRepository = eventsRepository;
        this.eventStatusService = eventStatusService;
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

    public List<EventsEntity> findAllByHierarchyAndIdEntity(HierarchyEntity hierarchy, Integer idEntity) {
        return eventsRepository.findAllByHierarchyAndIdEntity(hierarchy, idEntity);
    }

    public void approveEvent(EventsEntity event) {
        EventStatusEntity status = eventStatusService.findByStatusMessage(ConstantValues.EventStatus.APPROVED);
        event.setEventStatusByStatus(status);
        eventsRepository.save(event);
    }

    public void rejectEvent(EventsEntity event) {
        EventStatusEntity status = eventStatusService.findByStatusMessage(ConstantValues.EventStatus.DENIED_APPROVAL);
        event.setEventStatusByStatus(status);
        eventsRepository.save(event);
    }

    public void cancelEvent(EventsEntity event) {
        EventStatusEntity status = eventStatusService.findByStatusMessage(ConstantValues.EventStatus.CANCELLED);
        event.setEventStatusByStatus(status);
        eventsRepository.save(event);
    }

    @Transactional
    public void delete(EventsEntity event) {
        eventsRepository.delete(event);
    }
}