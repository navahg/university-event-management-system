package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.RolesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UsersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class UsersService {

    private UsersRepository usersRepository;
    private RolesService rolesService;

    @Autowired
    public UsersService(UsersRepository usersRepository, RolesService rolesService) {
        this.usersRepository = usersRepository;
        this.rolesService = rolesService;
    }

    public UsersEntity create() {
        return new UsersEntity();
    }

    public UsersEntity save(UsersEntity user) {
        return usersRepository.save(user);
    }

    public List<UsersEntity> findByRolesLike(String role) {
        List<UsersEntity> users = new ArrayList<>();
        for (RolesEntity rolesEntity : rolesService.findAllByNamesLike(role)) {
            users.addAll(usersRepository.findByRole(rolesEntity));
        }
        return users;
    }

    public UsersEntity findByEmail(String email) {
        Optional<UsersEntity> result = usersRepository.findByEmail(email);
        return result.orElse(null);
    }

    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }
}