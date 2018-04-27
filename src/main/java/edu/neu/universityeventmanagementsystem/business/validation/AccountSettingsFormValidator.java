package edu.neu.universityeventmanagementsystem.business.validation;

import edu.neu.universityeventmanagementsystem.business.ui.users.account.view.AccountSettingsView;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * AccountSettingsFormValidator class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/27/2018
 */
@Component
public class AccountSettingsFormValidator extends ValidationHelper implements Validator<AccountSettingsView> {
    @Override
    public Optional<ValidationError> validate(AccountSettingsView accountSettingsView) {

        try {
            validate(accountSettingsView.getFirstName(), AccountSettingsView.NAME_ERROR, NOT_NULL, NOT_EMPTY, ALPHABETS);
            validate(accountSettingsView.getLastName(), AccountSettingsView.NAME_ERROR, NOT_NULL, NOT_EMPTY, ALPHABETS);
            validate(accountSettingsView.getEmail(), AccountSettingsView.EMAIL_ERROR, NOT_NULL, NOT_EMPTY, EMAIL);
        } catch (ValidationError e) {
            return Optional.of(e);
        }

        return Optional.empty();
    }
}
