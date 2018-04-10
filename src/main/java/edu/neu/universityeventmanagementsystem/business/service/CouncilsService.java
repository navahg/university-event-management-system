package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.CouncilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CouncilsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class CouncilsService {

    private CouncilsRepository councilsRepository;

    @Autowired
    public CouncilsService (CouncilsRepository councilsRepository) {
        this.councilsRepository = councilsRepository;
    }
}