package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.InvitesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * InvitesRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface InvitesRepository extends JpaRepository<InvitesEntity, Integer> {

    @Query("SELECT i FROM InvitesEntity i WHERE i.usersByIdInvitee = :invitee")
    List<InvitesEntity> findAllByInvitee(@Param("invitee") UsersEntity invitee);

    @Transactional
    @Modifying
    @Query("DELETE FROM InvitesEntity i WHERE i.usersByIdInvitee = :invitee AND i.eventsByIdEvent = :event")
    int deleteByInviteeAndEvent(@Param("invitee") UsersEntity invitee, @Param("event") EventsEntity event);
}