package edu.neu.universityeventmanagementsystem.business.validation;

import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.AddUserView;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * AddUserFormValidator class
 *
 * @author Dinesh Deivamani<deivamani.d@husky.neu.edu>
 * @version 1.0
 * @since Apr. 27, 2018
 */
@Component
public class AddUserFormValidator extends ValidationHelper implements Validator<AddUserView> {

    @Override
    public Optional<ValidationError> validate(AddUserView addUserView) {

        try {
            validate(addUserView.getFirstName(), AddUserView.FIRSTNAME_ERROR, NOT_EMPTY, ALPHABETS, NOT_NULL);
            validate(addUserView.getEmail(), AddUserView.EMAIL_ERROR_LABEL, NOT_EMPTY, NOT_NULL, EMAIL);
            validate(addUserView.getLastName(), AddUserView.LASTNAME_ERROR, NOT_EMPTY, ALPHABETS, NOT_NULL);
            validate(addUserView.getPassword(), addUserView.getConfirmPassword(), AddUserView.PASSWORD_ERROR_LABEL, NOT_EMPTY, NOT_NULL, PASSWORD);
            validate(addUserView.getUserName(), AddUserView.USERNAME_ERROR_LABEL, NOT_EMPTY, NOT_NULL, ALPHANUMERIC);
        } catch (ValidationError a) {
            return Optional.of(a);
        }

        return Optional.empty();
    }
}
