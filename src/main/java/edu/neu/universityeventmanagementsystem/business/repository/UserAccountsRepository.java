package edu.neu.universityeventmanagementsystem.business.repository;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserAccountsRepository class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Integer> {

    @Query("SELECT a FROM UserAccountsEntity a " +
            "WHERE " +
            "(LOWER(a.userName) = LOWER(:userName) OR LOWER(a.usersByIdUser.email) = LOWER(:userName))" +
            "AND " +
            "a.password = :password")
    public List<UserAccountsEntity> authorizeUser(@Param("userName") String userName, @Param("password") String password);
}