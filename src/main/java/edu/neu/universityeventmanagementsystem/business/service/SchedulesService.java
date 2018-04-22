package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.SchedulesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SchedulesService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class SchedulesService {

    private SchedulesRepository schedulesRepository;

    @Autowired
    public SchedulesService(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    public List<SchedulesEntity> findByUser(UsersEntity user) {
        return schedulesRepository.findAllByUser(user);
    }
}