package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * CouncilsEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "councils", schema = "university_event_management_system")
public class CouncilsEntity {
    private int idCouncil;
    private String name;
    private Collection<CouncilMembersEntity> councilMembersByIdCouncil;
    private CollegesEntity collegesByIdCollege;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_council", nullable = false)
    public int getIdCouncil() {
        return idCouncil;
    }

    public void setIdCouncil(int idCouncil) {
        this.idCouncil = idCouncil;
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
        CouncilsEntity that = (CouncilsEntity) o;
        return idCouncil == that.idCouncil &&
                collegesByIdCollege.equals(that.getCollegesByIdCollege()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCouncil, collegesByIdCollege.getIdCollege(), name);
    }

    @OneToMany(mappedBy = "councilsByIdCouncil")
    public Collection<CouncilMembersEntity> getCouncilMembersByIdCouncil() {
        return councilMembersByIdCouncil;
    }

    public void setCouncilMembersByIdCouncil(Collection<CouncilMembersEntity> councilMembersByIdCouncil) {
        this.councilMembersByIdCouncil = councilMembersByIdCouncil;
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
