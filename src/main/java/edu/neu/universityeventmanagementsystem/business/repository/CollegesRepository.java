package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.CollegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CollegesRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface CollegesRepository extends JpaRepository<CollegesEntity, Integer> {

    @Query("SELECT c FROM CollegesEntity c WHERE LOWER(c.name) = LOWER(:name)")
    List<CollegesEntity> findByName(@Param("name") String name);
}
