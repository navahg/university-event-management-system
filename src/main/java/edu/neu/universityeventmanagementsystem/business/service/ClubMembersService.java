package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.ClubMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClubMembersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class ClubMembersService {

    private ClubMembersRepository clubMembersRepository;

    @Autowired
    public ClubMembersService(ClubMembersRepository clubMembersRepository) {
        this.clubMembersRepository = clubMembersRepository;
    }
}
