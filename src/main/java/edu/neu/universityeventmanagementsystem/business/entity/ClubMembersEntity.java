package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClubMembersEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "club_members", schema = "university_event_management_system")
public class ClubMembersEntity {
    private int idClubMembers;
    private UsersEntity usersByIdUser;
    private ClubsEntity clubsByIdClub;

    @Id
    @Column(name = "id_club_members", nullable = false)
    public int getIdClubMembers() {
        return idClubMembers;
    }

    public void setIdClubMembers(int idClubMembers) {
        this.idClubMembers = idClubMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubMembersEntity that = (ClubMembersEntity) o;
        return idClubMembers == that.idClubMembers &&
                usersByIdUser.equals(that.getUsersByIdUser()) &&
                clubsByIdClub.equals(that.getClubsByIdClub());
    }

    @Override
    public int hashCode() {

        return Objects.hash(idClubMembers, usersByIdUser.getIdUser(), clubsByIdClub.getIdClub());
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
    @JoinColumn(name = "id_club", referencedColumnName = "id_club", nullable = false)
    public ClubsEntity getClubsByIdClub() {
        return clubsByIdClub;
    }

    public void setClubsByIdClub(ClubsEntity clubsByIdClub) {
        this.clubsByIdClub = clubsByIdClub;
    }
}
