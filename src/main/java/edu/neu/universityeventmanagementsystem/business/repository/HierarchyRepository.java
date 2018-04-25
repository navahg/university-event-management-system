package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * HierarchyRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface HierarchyRepository extends JpaRepository<HierarchyEntity, Integer> {

    @Query("SELECT h FROM HierarchyEntity h WHERE LOWER(h.tableName) = LOWER(:tableName)")
    Optional<HierarchyEntity> findByTableName(@Param("tableName") String tableName);
}