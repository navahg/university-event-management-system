package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.ClubsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ClubsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface ClubsRepository extends JpaRepository<ClubsEntity, Integer> {

    @Query("SELECT c FROM ClubsEntity c WHERE c.name = :name")
    Optional<ClubsEntity> findOneByName(@Param("name") String name);
}
