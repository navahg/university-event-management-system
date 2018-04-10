package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.ProgramsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProgramsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class ProgramsService {

    private ProgramsRepository programsRepository;

    @Autowired
    public ProgramsService(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }
}