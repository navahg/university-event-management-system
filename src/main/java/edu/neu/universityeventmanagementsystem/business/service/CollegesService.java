package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.CollegesEntity;
import edu.neu.universityeventmanagementsystem.business.repository.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CollegesService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class CollegesService {

    private CollegesRepository collegesRepository;

    @Autowired
    public CollegesService(CollegesRepository collegesRepository) {
        this.collegesRepository = collegesRepository;
    }

    public List<CollegesEntity> findAll() {
        return collegesRepository.findAll();
    }

    public CollegesEntity findOneByName(String name) {
        Optional<CollegesEntity> result = collegesRepository.findByName(name);
        return result.orElse(null);
    }

    public CollegesEntity save(CollegesEntity collegesEntity) {
        return collegesRepository.save(collegesEntity);
    }

    public CollegesEntity create() {
        return new CollegesEntity();
    }

    @Transactional
    public void delete(CollegesEntity collegesEntity) {
        collegesRepository.delete(collegesEntity);
    }

    public CollegesEntity findById(int id) {
        Optional<CollegesEntity> result = collegesRepository.findById(id);
        return result.orElse(null);
    }
}
