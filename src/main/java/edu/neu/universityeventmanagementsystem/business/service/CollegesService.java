package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.repository.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
