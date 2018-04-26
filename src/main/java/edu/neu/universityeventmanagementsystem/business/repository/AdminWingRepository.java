package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.AdminWingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * AdminWingRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface AdminWingRepository extends JpaRepository<AdminWingEntity, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM AdminWingEntity a WHERE a.name = :name")
    void deleteByName(@Param("name") String name);

    @Query("SELECT a FROM AdminWingEntity a WHERE a.name = :name")
    Optional<AdminWingEntity> findOneByName(@Param("name") String name);
}
