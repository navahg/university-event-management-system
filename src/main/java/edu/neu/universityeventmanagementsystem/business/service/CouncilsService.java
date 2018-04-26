package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.CouncilsEntity;
import edu.neu.universityeventmanagementsystem.business.repository.CouncilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CouncilsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class CouncilsService {

    private CouncilsRepository councilsRepository;

    @Autowired
    public CouncilsService(CouncilsRepository councilsRepository) {
        this.councilsRepository = councilsRepository;
    }

    public CouncilsEntity create() {
        return new CouncilsEntity();
    }

    public CouncilsEntity save(CouncilsEntity councilsEntity) {
        return councilsRepository.save(councilsEntity);
    }

    @Transactional
    public void deleteByName(String name) {
        councilsRepository.deleteByName(name);
    }

    public List<CouncilsEntity> findAll() {
        return councilsRepository.findAll();
    }

    public CouncilsEntity findOneByName(String name) {
        Optional<CouncilsEntity> result = councilsRepository.findOneByName(name);
        return result.orElse(null);
    }

    public CouncilsEntity findById(int id) {
        Optional<CouncilsEntity> result = councilsRepository.findById(id);
        return result.orElse(null);
    }
}