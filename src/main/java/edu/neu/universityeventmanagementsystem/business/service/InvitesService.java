package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.InvitesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * InvitesService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class InvitesService {

    private InvitesRepository invitesRepository;

    @Autowired
    public InvitesService(InvitesRepository invitesRepository) {
        this.invitesRepository = invitesRepository;
    }
}