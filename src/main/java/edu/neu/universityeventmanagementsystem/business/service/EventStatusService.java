package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.EventStatusEntity;
import edu.neu.universityeventmanagementsystem.business.repository.EventStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * EventStatusService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class EventStatusService {

    private EventStatusRepository eventStatusRepository;

    @Autowired
    public EventStatusService(EventStatusRepository eventStatusRepository) {
        this.eventStatusRepository = eventStatusRepository;
    }

    public EventStatusEntity findByStatusMessage(String status) {
        Optional<EventStatusEntity> result = eventStatusRepository.findByStatusMessage(status);
        return result.orElse(null);
    }
}