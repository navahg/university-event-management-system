package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.CollegesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.ProgramsEntity;
import edu.neu.universityeventmanagementsystem.business.repository.CollegesRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        List<CollegesEntity> colleges = collegesRepository.findByName(name);
        if(colleges.size() == 0)
            return null;
        return colleges.get(0);
    }

    @Transactional
    public List<ProgramsEntity> getAllPrograms(CollegesEntity collegesEntity) {
        return new ArrayList<>(collegesEntity.getProgramsByIdCollege());
    }
}
