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
    private UsersEntity usersByIdUser;
    private ProgramsEntity programsByIdProgram;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_program_members", nullable = false)
    public int getIdProgramMembers() {
        return idProgramMembers;
    }

    public void setIdProgramMembers(int idProgramMembers) {
        this.idProgramMembers = idProgramMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramMembersEntity that = (ProgramMembersEntity) o;
        return idProgramMembers == that.idProgramMembers &&
                usersByIdUser.equals(that.getUsersByIdUser()) &&
                programsByIdProgram.equals(that.getProgramsByIdProgram());
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProgramMembers, usersByIdUser.getIdUser(), programsByIdProgram.getIdProgram());
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
