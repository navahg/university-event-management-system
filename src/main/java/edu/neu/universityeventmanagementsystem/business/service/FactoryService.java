package edu.neu.universityeventmanagementsystem.business.service;

import edu.neu.universityeventmanagementsystem.business.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * FactoryService class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/26/2018
 */
@Service
public class FactoryService {

    private static final String COLLEGE_TABLE = "colleges";
    private static final String PROGRAM_TABLE = "programs";
    private static final String COUNCIL_TABLE = "councils";
    private static final String ADMIN_WING_TABLE = "admin_wing";
    private static final String CLUB_TABLE = "clubs";

    private CollegesService collegesService;
    private ProgramsService programsService;
    private CouncilsService councilsService;
    private AdminWingService adminWingService;
    private ClubsService clubsService;
    private UsersService usersService;

    @Autowired
    public FactoryService(CollegesService collegesService,
                          ProgramsService programsService,
                          CouncilsService councilsService,
                          AdminWingService adminWingService,
                          ClubsService clubsService,
                          UsersService usersService) {
        this.collegesService = collegesService;
        this.programsService = programsService;
        this.councilsService = councilsService;
        this.adminWingService = adminWingService;
        this.clubsService = clubsService;
        this.usersService = usersService;
    }

    public List<String> getAllNameFromTable(String table) {
        List<String> result = new ArrayList<>();

        if (Objects.equals(COLLEGE_TABLE, table)) {
            collegesService.findAll().forEach(collegesEntity -> result.add(collegesEntity.getName()));
        } else if (Objects.equals(PROGRAM_TABLE, table)) {
            programsService.findAll().forEach(programsEntity -> result.add(programsEntity.getName()));
        } else if (Objects.equals(ADMIN_WING_TABLE, table)) {
            adminWingService.findAll().forEach(adminWingEntity -> result.add(adminWingEntity.getName()));
        } else if (Objects.equals(COUNCIL_TABLE, table)) {
            councilsService.findAll().forEach(councilsEntity -> result.add(councilsEntity.getName()));
        } else if (Objects.equals(CLUB_TABLE, table)) {
            clubsService.findAll().forEach(clubsEntity -> result.add(clubsEntity.getName()));
        }

        return result;
    }

    public List<String> getAllOrganizations(CollegesEntity college, String table) {
        List<String> result = new ArrayList<>();

        if (Objects.equals(PROGRAM_TABLE, table)) {
            college.getProgramsByIdCollege().forEach(programsEntity -> result.add(programsEntity.getName()));
        } else if (Objects.equals(ADMIN_WING_TABLE, table)) {
            college.getAdminWingsByIdCollege().forEach(adminWingEntity -> result.add(adminWingEntity.getName()));
        } else if (Objects.equals(COUNCIL_TABLE, table)) {
            college.getCouncilsByIdCollege().forEach(councilsEntity -> result.add(councilsEntity.getName()));
        }

        return result;
    }

    public Map.Entry<String, Integer> findOrganizationOfUser(UsersEntity user) {
        Map<String, Integer> result = new HashMap<>(1);
        if (user.getProgramMembersByIdUser() != null) {
            result.put("programs", user.getProgramMembersByIdUser().getProgramsByIdProgram().getIdProgram());
        } else if (user.getAdminWingMembersByIdUser() != null) {
            result.put("admin_wing", user.getAdminWingMembersByIdUser().getAdminWingByIdAdminWing().getIdAdminWing());
        } else if (user.getCouncilMembersByIdUser() != null) {
            result.put("councils", user.getCouncilMembersByIdUser().getCouncilsByIdCouncil().getIdCouncil());
        } else if (user.getCollegeMembersByIdUser() != null) {
            result.put("colleges", user.getCollegeMembersByIdUser().getCollegesByIdCollege().getIdCollege());
        } else {
            result.put("university", 0);
        }

        return result.entrySet().iterator().next();
    }

    public Map.Entry<String, String> findOrganizationNameOfUser(UsersEntity user) {
        Map<String, String> result = new HashMap<>(1);
        if (user.getProgramMembersByIdUser() != null) {
            result.put("programs", user.getProgramMembersByIdUser().getProgramsByIdProgram().getName());
        } else if (user.getAdminWingMembersByIdUser() != null) {
            result.put("admin_wing", user.getAdminWingMembersByIdUser().getAdminWingByIdAdminWing().getName());
        } else if (user.getCouncilMembersByIdUser() != null) {
            result.put("councils", user.getCouncilMembersByIdUser().getCouncilsByIdCouncil().getName());
        } else if (user.getCollegeMembersByIdUser() != null) {
            result.put("colleges", user.getCollegeMembersByIdUser().getCollegesByIdCollege().getName());
        } else {
            result.put("university", "university");
        }

        return result.entrySet().iterator().next();
    }

    public int findEntityId(String table, String recordName) {
        if (Objects.equals(COLLEGE_TABLE, table)) {
            CollegesEntity result = collegesService.findOneByName(recordName);
            if (result != null)
                return result.getIdCollege();
            return -1;
        } else if (Objects.equals(PROGRAM_TABLE, table)) {
            ProgramsEntity result = programsService.findByName(recordName);
            if (result != null)
                return result.getIdProgram();
            return -1;
        } else if (Objects.equals(ADMIN_WING_TABLE, table)) {
            AdminWingEntity result = adminWingService.findByName(recordName);
            if (result != null)
                return result.getIdAdminWing();
            return -1;
        } else if (Objects.equals(COUNCIL_TABLE, table)) {
            CouncilsEntity result = councilsService.findByName(recordName);
            if (result != null)
                return result.getIdCouncil();
            return -1;
        } else if (Objects.equals(CLUB_TABLE, table)) {
            ClubsEntity result = clubsService.findOneByName(recordName);
            if (result != null)
                return result.getIdClub();
            return -1;
        }
        return -1;
    }

    public List<UsersEntity> getAllUsers(String table, int id) {
        List<UsersEntity> users = new ArrayList<>();
        if (Objects.equals(COLLEGE_TABLE, table)) {
            CollegesEntity result = collegesService.findById(id);
            if (result != null)
                for (CollegeMembersEntity entity : result.getCollegeMembersByIdCollege()) {
                    users.add(entity.getUsersByIdUser());
                }
            return users;
        } else if (Objects.equals(PROGRAM_TABLE, table)) {
            ProgramsEntity result = programsService.findById(id);
            if (result != null)
                for (ProgramMembersEntity entity : result.getProgramMembersByIdProgram()) {
                    users.add(entity.getUsersByIdUser());
                }
            return users;
        } else if (Objects.equals(ADMIN_WING_TABLE, table)) {
            AdminWingEntity result = adminWingService.findById(id);
            if (result != null)
                for (AdminWingMembersEntity entity : result.getAdminWingMembersByIdAdminWing()) {
                    users.add(entity.getUsersByIdUser());
                }
            return users;
        } else if (Objects.equals(COUNCIL_TABLE, table)) {
            CouncilsEntity result = councilsService.findById(id);
            if (result != null)
                for (CouncilMembersEntity entity : result.getCouncilMembersByIdCouncil()) {
                    users.add(entity.getUsersByIdUser());
                }
            return users;
        } else if (Objects.equals(CLUB_TABLE, table)) {
            ClubsEntity result = clubsService.findById(id);
            if (result != null)
                for (ClubMembersEntity entity : result.getClubMembersByIdClub()) {
                    users.add(entity.getUsersByIdUser());
                }
            return users;
        }
        return usersService.findAll();
    }
}
