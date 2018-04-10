package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.AdminWingMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AdminWingMembersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class AdminWingMembersService {

    private AdminWingMembersRepository adminWingMembersRepository;

    @Autowired
    public AdminWingMembersService(AdminWingMembersRepository adminWingMembersRepository) {
        this.adminWingMembersRepository = adminWingMembersRepository;
    }
}
