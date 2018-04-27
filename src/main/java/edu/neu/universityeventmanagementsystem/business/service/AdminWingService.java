package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.AdminWingEntity;
import edu.neu.universityeventmanagementsystem.business.repository.AdminWingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * AdminWingService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class AdminWingService {

    private AdminWingRepository adminWingRepository;

    @Autowired
    public AdminWingService(AdminWingRepository adminWingRepository) {
        this.adminWingRepository = adminWingRepository;
    }

    public AdminWingEntity create() {
        return new AdminWingEntity();
    }

    public AdminWingEntity save(AdminWingEntity adminWingEntity) {
        return adminWingRepository.save(adminWingEntity);
    }

    public void deleteByName(String name) {
        adminWingRepository.deleteByName(name);
    }

    public List<AdminWingEntity> findAll() {
        return adminWingRepository.findAll();
    }

    public AdminWingEntity findByName(String name) {
        Optional<AdminWingEntity> result = adminWingRepository.findOneByName(name);
        return result.orElse(null);
    }

    public AdminWingEntity findById(int id) {
        Optional<AdminWingEntity> result = adminWingRepository.findById(id);
        return result.orElse(null);
    }
}
