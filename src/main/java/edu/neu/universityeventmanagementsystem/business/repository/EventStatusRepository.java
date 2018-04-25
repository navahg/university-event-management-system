package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.EventStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * EventStatusRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface EventStatusRepository extends JpaRepository<EventStatusEntity, Integer> {

    @Query("SELECT e FROM EventStatusEntity e WHERE LOWER(e.statusMessage) = LOWER(:status)")
    Optional<EventStatusEntity> findByStatusMessage(@Param("status") String status);
}