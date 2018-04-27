package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.ProgramsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ProgramsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface ProgramsRepository extends JpaRepository<ProgramsEntity, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ProgramsEntity p WHERE p.name = :name")
    void deleteByName(@Param("name") String name);

    @Query("SELECT p FROM ProgramsEntity p WHERE p.name = :name")
    Optional<ProgramsEntity> findByName(@Param("name") String name);
}