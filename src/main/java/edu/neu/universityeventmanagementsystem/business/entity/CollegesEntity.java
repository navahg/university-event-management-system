package edu.neu.universityeventmanagementsystem.business.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * CollegesEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "colleges", schema = "university_event_management_system")
public class CollegesEntity {
    private int idCollege;
    private String name;
    private Collection<AdminWingEntity> adminWingsByIdCollege;
    private Collection<CouncilsEntity> councilsByIdCollege;
    private Collection<ProgramsEntity> programsByIdCollege;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_college", nullable = false)
    public int getIdCollege() {
        return idCollege;
    }

    public void setIdCollege(int idCollege) {
        this.idCollege = idCollege;
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
        CollegesEntity that = (CollegesEntity) o;
        return idCollege == that.idCollege &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCollege, name);
    }

    @OneToMany(mappedBy = "collegesByIdCollege", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public Collection<AdminWingEntity> getAdminWingsByIdCollege() {
        return adminWingsByIdCollege;
    }

    public void setAdminWingsByIdCollege(Collection<AdminWingEntity> adminWingsByIdCollege) {
        this.adminWingsByIdCollege = adminWingsByIdCollege;
    }

    @OneToMany(mappedBy = "collegesByIdCollege", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public Collection<CouncilsEntity> getCouncilsByIdCollege() {
        return councilsByIdCollege;
    }

    public void setCouncilsByIdCollege(Collection<CouncilsEntity> councilsByIdCollege) {
        this.councilsByIdCollege = councilsByIdCollege;
    }

    @OneToMany(mappedBy = "collegesByIdCollege", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public Collection<ProgramsEntity> getProgramsByIdCollege() {
        return programsByIdCollege;
    }

    public void setProgramsByIdCollege(Collection<ProgramsEntity> programsByIdCollege) {
        this.programsByIdCollege = programsByIdCollege;
    }
}
