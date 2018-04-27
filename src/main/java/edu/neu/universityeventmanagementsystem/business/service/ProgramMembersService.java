package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.ProgramMembersEntity;
import edu.neu.universityeventmanagementsystem.business.repository.ProgramMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProgramMembersService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class ProgramMembersService {

    private ProgramMembersRepository programMembersRepository;

    @Autowired
    public ProgramMembersService(ProgramMembersRepository programMembersRepository) {
        this.programMembersRepository = programMembersRepository;
    }

    public ProgramMembersEntity create() {
        return new ProgramMembersEntity();
    }

    public ProgramMembersEntity save(ProgramMembersEntity programMembersEntity) {
        return programMembersRepository.save(programMembersEntity);
    }
}