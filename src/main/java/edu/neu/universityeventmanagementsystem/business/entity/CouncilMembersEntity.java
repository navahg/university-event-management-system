package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * CouncilMembersEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "council_members", schema = "university_event_management_system")
public class CouncilMembersEntity {
    private int idCouncilMembers;
    private int idUser;
    private int idCouncil;
    private UsersEntity usersByIdUser;
    private CouncilsEntity councilsByIdCouncil;

    @Id
    @Column(name = "id_council_members", nullable = false)
    public int getIdCouncilMembers() {
        return idCouncilMembers;
    }

    public void setIdCouncilMembers(int idCouncilMembers) {
        this.idCouncilMembers = idCouncilMembers;
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
    @Column(name = "id_council", nullable = false)
    public int getIdCouncil() {
        return idCouncil;
    }

    public void setIdCouncil(int idCouncil) {
        this.idCouncil = idCouncil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouncilMembersEntity that = (CouncilMembersEntity) o;
        return idCouncilMembers == that.idCouncilMembers &&
                idUser == that.idUser &&
                idCouncil == that.idCouncil;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCouncilMembers, idUser, idCouncil);
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
    @JoinColumn(name = "id_council", referencedColumnName = "id_council", nullable = false)
    public CouncilsEntity getCouncilsByIdCouncil() {
        return councilsByIdCouncil;
    }

    public void setCouncilsByIdCouncil(CouncilsEntity councilsByIdCouncil) {
        this.councilsByIdCouncil = councilsByIdCouncil;
    }
}
