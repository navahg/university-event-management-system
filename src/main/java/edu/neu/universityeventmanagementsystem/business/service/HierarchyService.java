package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.repository.HierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * HierarchyService class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Service
public class HierarchyService {

    private HierarchyRepository hierarchyRepository;

    @Autowired
    public HierarchyService(HierarchyRepository hierarchyRepository) {
        this.hierarchyRepository = hierarchyRepository;
    }

    public List<HierarchyEntity> findAll() {
        return hierarchyRepository.findAll();
    }

    public HierarchyEntity findByTableName(String tableName) {
        Optional<HierarchyEntity> result = hierarchyRepository.findByTableName(tableName);
        return result.orElse(null);
    }

    public HierarchyEntity findByLevel(int level) {
        Optional<HierarchyEntity> result = hierarchyRepository.findByLevel(level);
        return result.orElse(null);
    }
}