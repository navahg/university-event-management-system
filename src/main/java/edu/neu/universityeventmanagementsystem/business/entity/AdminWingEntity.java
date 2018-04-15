package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * AdminWingEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "admin_wing", schema = "university_event_management_system")
public class AdminWingEntity {
    private int idAdminWing;
    private String name;
    private CollegesEntity collegesByIdCollege;
    private Collection<AdminWingMembersEntity> adminWingMembersByIdAdminWing;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_wing", nullable = false)
    public int getIdAdminWing() {
        return idAdminWing;
    }

    public void setIdAdminWing(int idAdminWing) {
        this.idAdminWing = idAdminWing;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminWingEntity that = (AdminWingEntity) o;
        return idAdminWing == that.idAdminWing &&
                collegesByIdCollege.equals(that.getCollegesByIdCollege()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAdminWing, collegesByIdCollege.getIdCollege(), name);
    }

    @ManyToOne
    @JoinColumn(name = "id_college", referencedColumnName = "id_college", nullable = false)
    public CollegesEntity getCollegesByIdCollege() {
        return collegesByIdCollege;
    }

    public void setCollegesByIdCollege(CollegesEntity collegesByIdCollege) {
        this.collegesByIdCollege = collegesByIdCollege;
    }

    @OneToMany(mappedBy = "adminWingByIdAdminWing")
    public Collection<AdminWingMembersEntity> getAdminWingMembersByIdAdminWing() {
        return adminWingMembersByIdAdminWing;
    }

    public void setAdminWingMembersByIdAdminWing(Collection<AdminWingMembersEntity> adminWingMembersByIdAdminWing) {
        this.adminWingMembersByIdAdminWing = adminWingMembersByIdAdminWing;
    }
}
