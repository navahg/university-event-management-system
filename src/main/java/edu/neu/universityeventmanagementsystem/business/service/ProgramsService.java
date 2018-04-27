package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.ProgramsEntity;
import edu.neu.universityeventmanagementsystem.business.repository.ProgramsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProgramsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class ProgramsService {

    private ProgramsRepository programsRepository;

    @Autowired
    public ProgramsService(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }

    public ProgramsEntity create() {
        return new ProgramsEntity();
    }

    public ProgramsEntity save(ProgramsEntity programsEntity) {
        return programsRepository.save(programsEntity);
    }

    public void deleteByName(String name) {
        programsRepository.deleteByName(name);
    }

    public List<ProgramsEntity> findAll() {
        return programsRepository.findAll();
    }

    public ProgramsEntity findByName(String name) {
        Optional<ProgramsEntity> result = programsRepository.findByName(name);
        return result.orElse(null);
    }

    public ProgramsEntity findById(int id) {
        Optional<ProgramsEntity> result = programsRepository.findById(id);
        return result.orElse(null);
    }
}