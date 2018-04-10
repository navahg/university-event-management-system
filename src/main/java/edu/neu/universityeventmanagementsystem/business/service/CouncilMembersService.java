package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.CouncilMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CouncilMembersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class CouncilMembersService {

    private CouncilMembersRepository councilMembersRepository;

    @Autowired
    public CouncilMembersService(CouncilMembersRepository councilMembersRepository) {
        this.councilMembersRepository = councilMembersRepository;
    }
}