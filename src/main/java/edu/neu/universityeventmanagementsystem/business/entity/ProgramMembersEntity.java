package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * ProgramMembersEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "program_members", schema = "university_event_management_system")
public class ProgramMembersEntity {
    private int idProgramMembers;
    private int idUser;
    private int idProgram;
    private UsersEntity usersByIdUser;
    private ProgramsEntity programsByIdProgram;

    @Id
    @Column(name = "id_program_members", nullable = false)
    public int getIdProgramMembers() {
        return idProgramMembers;
    }

    public void setIdProgramMembers(int idProgramMembers) {
        this.idProgramMembers = idProgramMembers;
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
    @Column(name = "id_program", nullable = false)
    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramMembersEntity that = (ProgramMembersEntity) o;
        return idProgramMembers == that.idProgramMembers &&
                idUser == that.idUser &&
                idProgram == that.idProgram;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProgramMembers, idUser, idProgram);
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
    @JoinColumn(name = "id_program", referencedColumnName = "id_program", nullable = false)
    public ProgramsEntity getProgramsByIdProgram() {
        return programsByIdProgram;
    }

    public void setProgramsByIdProgram(ProgramsEntity programsByIdProgram) {
        this.programsByIdProgram = programsByIdProgram;
    }
}
