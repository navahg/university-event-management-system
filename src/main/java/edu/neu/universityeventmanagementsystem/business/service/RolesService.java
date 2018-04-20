package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.RolesEntity;
import edu.neu.universityeventmanagementsystem.business.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RolesService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class RolesService {

    private RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public RolesEntity findByName(String role) {
        return rolesRepository.findByName(role);
    }

    public List<RolesEntity> findAll() {
        return rolesRepository.findAll();
    }
}