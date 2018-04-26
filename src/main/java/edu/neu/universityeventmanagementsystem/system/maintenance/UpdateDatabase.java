package edu.neu.universityeventmanagementsystem.system.maintenance;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventStatusService;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * UpdateDatabase class
 *
 * @author  Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 26, 2018
 */
@Component
public final class UpdateDatabase {

    private EventsService eventsService;
    private EventStatusService eventStatusService;

    @Autowired
    public UpdateDatabase(EventsService eventsService,
                          EventStatusService eventStatusService) {
        this.eventsService = eventsService;
        this.eventStatusService = eventStatusService;
    }

    public void updateEventStatus() {
        for (EventsEntity eventsEntity : eventsService.findAll()) {
            if (eventsEntity.getEndTime().before(new Date())) {
                String status = ConstantValues.EventStatus.COMPLETED;
                if (Objects.equals(eventsEntity.getEventStatusByStatus().getStatusMessage(),
                        ConstantValues.EventStatus.PENDING_APPROVAL))
                    status = ConstantValues.EventStatus.EXPIRED;

                eventsEntity.setEventStatusByStatus(eventStatusService.findByStatusMessage(status));
                eventsService.save(eventsEntity);
            }
        }
    }
}
