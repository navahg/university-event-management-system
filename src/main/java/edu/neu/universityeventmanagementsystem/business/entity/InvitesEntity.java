package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * InvitesEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "invites", schema = "university_event_management_system")
public class InvitesEntity {
    private int idInvite;
    private int idInvitee;
    private int idEvent;
    private UsersEntity usersByIdInvitee;
    private EventsEntity eventsByIdEvent;

    @Id
    @Column(name = "id_invite", nullable = false)
    public int getIdInvite() {
        return idInvite;
    }

    public void setIdInvite(int idInvite) {
        this.idInvite = idInvite;
    }

    @Basic
    @Column(name = "id_invitee", nullable = false)
    public int getIdInvitee() {
        return idInvitee;
    }

    public void setIdInvitee(int idInvitee) {
        this.idInvitee = idInvitee;
    }

    @Basic
    @Column(name = "id_event", nullable = false)
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvitesEntity that = (InvitesEntity) o;
        return idInvite == that.idInvite &&
                idInvitee == that.idInvitee &&
                idEvent == that.idEvent;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idInvite, idInvitee, idEvent);
    }

    @ManyToOne
    @JoinColumn(name = "id_invitee", referencedColumnName = "id_user", nullable = false)
    public UsersEntity getUsersByIdInvitee() {
        return usersByIdInvitee;
    }

    public void setUsersByIdInvitee(UsersEntity usersByIdInvitee) {
        this.usersByIdInvitee = usersByIdInvitee;
    }

    @ManyToOne
    @JoinColumn(name = "id_event", referencedColumnName = "id_event", nullable = false)
    public EventsEntity getEventsByIdEvent() {
        return eventsByIdEvent;
    }

    public void setEventsByIdEvent(EventsEntity eventsByIdEvent) {
        this.eventsByIdEvent = eventsByIdEvent;
    }
}
