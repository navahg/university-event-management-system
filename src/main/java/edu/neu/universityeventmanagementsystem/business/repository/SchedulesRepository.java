package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.SchedulesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SchedulesRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface SchedulesRepository extends JpaRepository<SchedulesEntity, Integer> {

    @Query("SELECT S FROM SchedulesEntity s WHERE s.usersByIdUser = :user")
    List<SchedulesEntity> findAllByUser(@Param("user") UsersEntity user);
}