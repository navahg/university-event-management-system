package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.EventRequestEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * EventRequestRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface EventRequestRepository extends JpaRepository<EventRequestEntity, Integer> {

    @Query("SELECT e FROM EventRequestEntity e WHERE e.hierarchyByIdHierarchy = :hierarchy AND e.idEntity = :idEntity")
    List<EventRequestEntity> findAllByHierarchyAndIdEntity(@Param("hierarchy") HierarchyEntity hierarchy, @Param("idEntity") Integer idEntity);

    @Query("SELECT e FROM EventRequestEntity e WHERE e.eventsByIdEvent = :event")
    Optional<EventRequestEntity> findByEvent(@Param("event") EventsEntity event);
}