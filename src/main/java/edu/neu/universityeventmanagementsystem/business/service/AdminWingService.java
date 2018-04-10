package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.AdminWingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AdminWingService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class AdminWingService {

    private AdminWingRepository adminWingRepository;

    @Autowired
    public AdminWingService(AdminWingRepository adminWingRepository) {
        this.adminWingRepository = adminWingRepository;
    }
}
