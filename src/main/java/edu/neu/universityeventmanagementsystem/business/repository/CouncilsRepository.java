package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.CouncilsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CouncilsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface CouncilsRepository extends JpaRepository<CouncilsEntity, Integer> {

    @Modifying
    @Query("DELETE FROM CouncilsEntity c WHERE c.name = :name")
    void deleteByName(@Param("name") String name);

    @Query("SELECT c FROM CouncilsEntity c WHERE c.name = :name")
    Optional<CouncilsEntity> findOneByName(@Param("name") String name);
}