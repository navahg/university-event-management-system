package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.EventParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}