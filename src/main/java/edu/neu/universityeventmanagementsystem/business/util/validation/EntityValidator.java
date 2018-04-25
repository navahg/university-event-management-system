package edu.neu.universityeventmanagementsystem.business.util.validation;

import java.util.Optional;

/**
 * Generic Entity Validator interface
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public interface EntityValidator<E> {

    Optional<ValidationError> validate(E e);
}
