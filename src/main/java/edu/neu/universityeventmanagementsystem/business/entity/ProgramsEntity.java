package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * ProgramsEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "programs", schema = "university_event_management_system")
public class ProgramsEntity {
    private int idProgram;
    private int idCollege;
    private String name;
    private Collection<ProgramMembersEntity> programMembersByIdProgram;
    private CollegesEntity collegesByIdCollege;

    @Id
    @Column(name = "id_program", nullable = false)
    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    @Basic
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
        ProgramsEntity that = (ProgramsEntity) o;
        return idProgram == that.idProgram &&
                idCollege == that.idCollege &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProgram, idCollege, name);
    }

    @OneToMany(mappedBy = "programsByIdProgram")
    public Collection<ProgramMembersEntity> getProgramMembersByIdProgram() {
        return programMembersByIdProgram;
    }

    public void setProgramMembersByIdProgram(Collection<ProgramMembersEntity> programMembersByIdProgram) {
        this.programMembersByIdProgram = programMembersByIdProgram;
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
