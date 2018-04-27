package edu.neu.universityeventmanagementsystem.business.validation;

import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.AddUserView;
import java.util.Optional;


/**
 * AddUserFormValidator class
 * 
 * @author Dinesh Deivamani<deivamani.d@husky.neu.edu>
 * @since Apr. 27, 2018
 * @version 1.0
 */
public class AddUserFormValidator extends ValidationHelper implements Validator<AddUserView> 
{

    @Override
    public Optional<ValidationError> validate(AddUserView addUserView) {
        
    try {
            validate(addUserView.getFirstName(), addUserView.FIRSTNAME_ERROR, NOT_EMPTY,ALPHABETS, NOT_NULL);
            validate(addUserView.getEmail(), addUserView.EMAIL_ERROR_LABEL, NOT_EMPTY, NOT_NULL, EMAIL);
            validate(addUserView.getLastName(),addUserView.LASTNAME_ERROR, NOT_EMPTY,ALPHABETS, NOT_NULL);
            validate(addUserView.getMiddleName(),addUserView.MIDDLENAME_ERROR, ALPHABETS);
            validate(addUserView.getPassword(), addUserView.PASSWORD_ERROR_LABEL,NOT_EMPTY, NOT_NULL,PASSWORD);
            validate(addUserView.getUserName(), addUserView.USERNAME_ERROR_LABEL,NOT_EMPTY, NOT_NULL,ALPHANUMERIC);
        } 
        catch (ValidationError a) {
            return Optional.of(a);
        }

        return Optional.empty();
    
    }
}
