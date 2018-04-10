package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * AdminWingMembersEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "admin_wing_members", schema = "university_event_management_system")
public class AdminWingMembersEntity {
    private int idAdminWingMembers;
    private int idUser;
    private int idAdminWing;
    private UsersEntity usersByIdUser;
    private AdminWingEntity adminWingByIdAdminWing;

    @Id
    @Column(name = "id_admin_wing_members", nullable = false)
    public int getIdAdminWingMembers() {
        return idAdminWingMembers;
    }

    public void setIdAdminWingMembers(int idAdminWingMembers) {
        this.idAdminWingMembers = idAdminWingMembers;
    }

    @Basic
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_admin_wing", nullable = false)
    public int getIdAdminWing() {
        return idAdminWing;
    }

    public void setIdAdminWing(int idAdminWing) {
        this.idAdminWing = idAdminWing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminWingMembersEntity that = (AdminWingMembersEntity) o;
        return idAdminWingMembers == that.idAdminWingMembers &&
                idUser == that.idUser &&
                idAdminWing == that.idAdminWing;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAdminWingMembers, idUser, idAdminWing);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_admin_wing", referencedColumnName = "id_admin_wing", nullable = false)
    public AdminWingEntity getAdminWingByIdAdminWing() {
        return adminWingByIdAdminWing;
    }

    public void setAdminWingByIdAdminWing(AdminWingEntity adminWingByIdAdminWing) {
        this.adminWingByIdAdminWing = adminWingByIdAdminWing;
    }
}
