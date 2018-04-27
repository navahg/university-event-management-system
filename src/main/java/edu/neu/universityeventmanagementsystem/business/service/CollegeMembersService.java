package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.CollegeMembersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.CollegeMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CollegeMembersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class CollegeMembersService {

    private CollegeMembersRepository collegeMembersRepository;

    @Autowired
    public CollegeMembersService(CollegeMembersRepository collegeMembersRepository) {
        this.collegeMembersRepository = collegeMembersRepository;
    }

    public CollegeMembersEntity create() {
        return new CollegeMembersEntity();
    }

    public CollegeMembersEntity save(CollegeMembersEntity collegeMembersEntity) {
        return collegeMembersRepository.save(collegeMembersEntity);
    }
}