package edu.neu.universityeventmanagementsystem.business.validation;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.CreateEventView;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * EventValidator class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
@Component
public class EventValidator extends ValidationHelper implements Validator<EventsEntity> {

    @Override
    public Optional<ValidationError> validate(EventsEntity eventsEntity) {
        try {
            validate(eventsEntity.getName(), CreateEventView.NAME_ERROR, NOT_EMPTY, NOT_NULL);
            validate(eventsEntity.getVenue(), CreateEventView.LOCATION_ERROR, NOT_EMPTY, NOT_NULL);
            validate(eventsEntity.getStartTime(), eventsEntity.getEndTime(), CreateEventView.TIME_ERROR, LEGAL_DATE_RANGE);
            validate(eventsEntity.getHierarchyByIdHierarchy(), CreateEventView.AUDIENCE_ERROR, NOT_NULL);
            validate(eventsEntity.getIdEntity(), CreateEventView.AUDIENCE_ERROR, NOT_NULL);
        } catch (ValidationError e) {
            return Optional.of(e);
        }

        return Optional.empty();
    }
}
