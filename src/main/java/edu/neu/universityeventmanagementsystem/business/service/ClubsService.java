package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.ClubsEntity;
import edu.neu.universityeventmanagementsystem.business.repository.ClubsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ClubsService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class ClubsService {

    private ClubsRepository clubsRepository;

    @Autowired
    public ClubsService(ClubsRepository clubsRepository) {
        this.clubsRepository = clubsRepository;
    }

    public List<ClubsEntity> findAll() {
        return clubsRepository.findAll();
    }

    public ClubsEntity findOneByName(String name) {
        Optional<ClubsEntity> result = clubsRepository.findOneByName(name);
        return result.orElse(null);
    }

    public ClubsEntity findById(int id) {
        Optional<ClubsEntity> result = clubsRepository.findById(id);
        return result.orElse(null);
    }
}
