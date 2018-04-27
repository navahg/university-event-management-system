package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.EventParticipantsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EventParticipantsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface EventParticipantsRepository extends JpaRepository<EventParticipantsEntity, Integer> {

    @Query("SELECT e FROM EventParticipantsEntity e WHERE e.eventsByIdEvent = :event")
    List<EventParticipantsEntity> findAllByEvent(@Param("event") EventsEntity event);

    @Query("SELECT e FROM EventParticipantsEntity e WHERE e.usersByIdUser = :user")
    List<EventParticipantsEntity> findAllByUser(@Param("user") UsersEntity user);
}