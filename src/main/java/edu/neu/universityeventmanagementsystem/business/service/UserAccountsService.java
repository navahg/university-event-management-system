package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.repository.UserAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserAccountsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class UserAccountsService {

    private UserAccountsRepository userAccountsRepository;

    @Autowired
    public UserAccountsService(UserAccountsRepository userAccountsRepository) {
        this.userAccountsRepository = userAccountsRepository;
    }

    public UserAccountsEntity findOne (String identifier, String password) {
        for (UserAccountsEntity u : userAccountsRepository.findAll()) {
            if ((u.getUserName().equals(identifier) || u.getUsersByIdUser().getEmail().equals(identifier)) &&
                    u.getPassword().equals(password))
                return u;
        }

        return null;
    }
}