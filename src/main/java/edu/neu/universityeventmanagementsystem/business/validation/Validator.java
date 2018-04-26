package edu.neu.universityeventmanagementsystem.business.validation;

import java.util.Optional;

/**
 * Generic Entity SimpleValidator interface
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public interface Validator<E> {

    Optional<ValidationError> validate(E e);
}
