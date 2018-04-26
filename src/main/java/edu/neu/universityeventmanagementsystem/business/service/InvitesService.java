package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.InvitesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.InvitesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<InvitesEntity> findByInvitee(UsersEntity user) {
        return invitesRepository.findAllByInvitee(user);
    }

    public InvitesEntity create() {
        return new InvitesEntity();
    }

    public InvitesEntity save(InvitesEntity invite) {
        return invitesRepository.save(invite);
    }

    public int deleteByInviteeAndEvent(UsersEntity invitee, EventsEntity event) {
        return invitesRepository.deleteByInviteeAndEvent(invitee, event);
    }
}