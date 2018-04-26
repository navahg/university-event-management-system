package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.NotificationsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NotificationsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Integer> {

    @Query("SELECT n FROM NotificationsEntity n WHERE n.usersByIdUser = :user")
    List<NotificationsEntity> findAllByIdUser(@Param("user") UsersEntity user);
}