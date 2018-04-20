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
    private UsersEntity usersByIdUser;
    private CouncilsEntity councilsByIdCouncil;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_council_members", nullable = false)
    public int getIdCouncilMembers() {
        return idCouncilMembers;
    }

    public void setIdCouncilMembers(int idCouncilMembers) {
        this.idCouncilMembers = idCouncilMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouncilMembersEntity that = (CouncilMembersEntity) o;
        return idCouncilMembers == that.idCouncilMembers &&
                usersByIdUser.equals(that.getUsersByIdUser()) &&
                councilsByIdCouncil.equals(that.getCouncilsByIdCouncil());
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCouncilMembers, usersByIdUser.getIdUser(), councilsByIdCouncil.getIdCouncil());
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
    @JoinColumn(name = "id_council", referencedColumnName = "id_council", nullable = false)
    public CouncilsEntity getCouncilsByIdCouncil() {
        return councilsByIdCouncil;
    }

    public void setCouncilsByIdCouncil(CouncilsEntity councilsByIdCouncil) {
        this.councilsByIdCouncil = councilsByIdCouncil;
    }
}
