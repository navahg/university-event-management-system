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
    private UsersEntity usersByIdUser;
    private AdminWingEntity adminWingByIdAdminWing;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_wing_members", nullable = false)
    public int getIdAdminWingMembers() {
        return idAdminWingMembers;
    }

    public void setIdAdminWingMembers(int idAdminWingMembers) {
        this.idAdminWingMembers = idAdminWingMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminWingMembersEntity that = (AdminWingMembersEntity) o;
        return idAdminWingMembers == that.idAdminWingMembers &&
                usersByIdUser.equals(that.getUsersByIdUser()) &&
                adminWingByIdAdminWing.equals(that.getAdminWingByIdAdminWing());
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAdminWingMembers, usersByIdUser.getIdUser(), adminWingByIdAdminWing.getIdAdminWing());
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
