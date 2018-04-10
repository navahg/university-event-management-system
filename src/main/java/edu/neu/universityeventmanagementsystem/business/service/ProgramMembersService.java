package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.ProgramMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProgramMembersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class ProgramMembersService {

    private ProgramMembersRepository programMembersRepository;

    @Autowired
    public ProgramMembersService(ProgramMembersRepository programMembersRepository) {
        this.programMembersRepository = programMembersRepository;
    }
}