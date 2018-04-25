package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EventsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface EventsRepository extends JpaRepository<EventsEntity, Integer> {

    @Query("SELECT e FROM EventsEntity e WHERE e.usersByIdCreator = :idCreator")
    List<EventsEntity> findAllByIdCreator(@Param("idCreator") UsersEntity creator);
}