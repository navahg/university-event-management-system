package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.EventRequestEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.repository.EventRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * EventRequestService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class EventRequestService {

    private EventRequestRepository eventRequestRepository;

    @Autowired
    public EventRequestService(EventRequestRepository eventRequestRepository) {
        this.eventRequestRepository = eventRequestRepository;
    }

    public EventRequestEntity create() {
        return new EventRequestEntity();
    }

    public EventRequestEntity save(EventRequestEntity eventRequestEntity) {
        return eventRequestRepository.save(eventRequestEntity);
    }

    public List<EventRequestEntity> findAllByHierarchyAndIdEntity(HierarchyEntity hierarchy, Integer idEntity) {
        return eventRequestRepository.findAllByHierarchyAndIdEntity(hierarchy, idEntity);
    }

    public EventRequestEntity findByEvent(EventsEntity event) {
        Optional<EventRequestEntity> result = eventRequestRepository.findByEvent(event);
        return result.orElse(null);
    }

    @Transactional
    public void delete(EventRequestEntity request) {
        eventRequestRepository.delete(request);
    }
}