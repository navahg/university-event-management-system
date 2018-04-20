package edu.neu.universityeventmanagementsystem.business.beans;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;

/**
 * CurrentUserBean class - A spring bean to store current user
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/20/2018
 */
public class CurrentUserBean {

    private UsersEntity currentUser;

    public CurrentUserBean() {
        currentUser = null;
    }

    public UsersEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UsersEntity currentUser) {
        this.currentUser = currentUser;
    }
}
