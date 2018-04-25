package edu.neu.universityeventmanagementsystem.business.util.validation;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;

import java.util.Arrays;
import java.util.Optional;

/**
 * EventValidator class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public class EventValidator extends ValidationHelper implements EntityValidator<EventsEntity> {

    @Override
    public Optional<ValidationError> validate(EventsEntity eventsEntity) {
        if (isAnyNullOrEmpty(Arrays.asList(eventsEntity.getName(),
                                           eventsEntity.getVenue())))
            return Optional.of(new ValidationError("Cannot have empty fields"));

        return Optional.empty();
    }
}
