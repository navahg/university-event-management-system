package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * UsersEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "users", schema = "university_event_management_system")
public class UsersEntity {
    private int idUser;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Collection<AdminWingMembersEntity> adminWingMembersByIdUser;
    private Collection<ClubMembersEntity> clubMembersByIdUser;
    private Collection<CouncilMembersEntity> councilMembersByIdUser;
    private Collection<EventParticipantsEntity> eventParticipantsByIdUser;
    private Collection<EventsEntity> eventsByIdUser;
    private Collection<InvitesEntity> invitesByIdUser;
    private Collection<NotificationsEntity> notificationsByIdUser;
    private Collection<ProgramMembersEntity> programMembersByIdUser;
    private Collection<SchedulesEntity> schedulesByIdUser;
    private Collection<UserAccountsEntity> userAccountsByIdUser;
    private RolesEntity rolesByIdRole;

    @Id
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name", nullable = true, length = 255)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return idUser == that.idUser &&
                rolesByIdRole.equals(that.getRolesByIdRole()) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, rolesByIdRole.getIdRole(), userName, firstName, middleName, lastName, email);
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<AdminWingMembersEntity> getAdminWingMembersByIdUser() {
        return adminWingMembersByIdUser;
    }

    public void setAdminWingMembersByIdUser(Collection<AdminWingMembersEntity> adminWingMembersByIdUser) {
        this.adminWingMembersByIdUser = adminWingMembersByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<ClubMembersEntity> getClubMembersByIdUser() {
        return clubMembersByIdUser;
    }

    public void setClubMembersByIdUser(Collection<ClubMembersEntity> clubMembersByIdUser) {
        this.clubMembersByIdUser = clubMembersByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<CouncilMembersEntity> getCouncilMembersByIdUser() {
        return councilMembersByIdUser;
    }

    public void setCouncilMembersByIdUser(Collection<CouncilMembersEntity> councilMembersByIdUser) {
        this.councilMembersByIdUser = councilMembersByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<EventParticipantsEntity> getEventParticipantsByIdUser() {
        return eventParticipantsByIdUser;
    }

    public void setEventParticipantsByIdUser(Collection<EventParticipantsEntity> eventParticipantsByIdUser) {
        this.eventParticipantsByIdUser = eventParticipantsByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdCreator")
    public Collection<EventsEntity> getEventsByIdUser() {
        return eventsByIdUser;
    }

    public void setEventsByIdUser(Collection<EventsEntity> eventsByIdUser) {
        this.eventsByIdUser = eventsByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdInvitee")
    public Collection<InvitesEntity> getInvitesByIdUser() {
        return invitesByIdUser;
    }

    public void setInvitesByIdUser(Collection<InvitesEntity> invitesByIdUser) {
        this.invitesByIdUser = invitesByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<NotificationsEntity> getNotificationsByIdUser() {
        return notificationsByIdUser;
    }

    public void setNotificationsByIdUser(Collection<NotificationsEntity> notificationsByIdUser) {
        this.notificationsByIdUser = notificationsByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<ProgramMembersEntity> getProgramMembersByIdUser() {
        return programMembersByIdUser;
    }

    public void setProgramMembersByIdUser(Collection<ProgramMembersEntity> programMembersByIdUser) {
        this.programMembersByIdUser = programMembersByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<SchedulesEntity> getSchedulesByIdUser() {
        return schedulesByIdUser;
    }

    public void setSchedulesByIdUser(Collection<SchedulesEntity> schedulesByIdUser) {
        this.schedulesByIdUser = schedulesByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<UserAccountsEntity> getUserAccountsByIdUser() {
        return userAccountsByIdUser;
    }

    public void setUserAccountsByIdUser(Collection<UserAccountsEntity> userAccountsByIdUser) {
        this.userAccountsByIdUser = userAccountsByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    public RolesEntity getRolesByIdRole() {
        return rolesByIdRole;
    }

    public void setRolesByIdRole(RolesEntity rolesByIdRole) {
        this.rolesByIdRole = rolesByIdRole;
    }
}
