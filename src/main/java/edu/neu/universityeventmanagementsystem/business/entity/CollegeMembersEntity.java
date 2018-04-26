package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * CollegeMembersEntity class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/26/2018
 */
@Entity
@Table(name = "college_members", schema = "university_event_management_system")
public class CollegeMembersEntity {
    private int idCollegeMembers;
    private UsersEntity usersByIdUser;
    private CollegesEntity collegesByIdCollege;

    @Id
    @Column(name = "id_college_members")
    public int getIdCollegeMembers() {
        return idCollegeMembers;
    }

    public void setIdCollegeMembers(int idCollegeMembers) {
        this.idCollegeMembers = idCollegeMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollegeMembersEntity that = (CollegeMembersEntity) o;
        return idCollegeMembers == that.idCollegeMembers &&
                usersByIdUser.equals(that.usersByIdUser) &&
                collegesByIdCollege.equals(that.collegesByIdCollege);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCollegeMembers, usersByIdUser.getIdUser(), collegesByIdCollege.getIdCollege());
    }

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_college", referencedColumnName = "id_college", nullable = false)
    public CollegesEntity getCollegesByIdCollege() {
        return collegesByIdCollege;
    }

    public void setCollegesByIdCollege(CollegesEntity collegesByIdCollege) {
        this.collegesByIdCollege = collegesByIdCollege;
    }
}
