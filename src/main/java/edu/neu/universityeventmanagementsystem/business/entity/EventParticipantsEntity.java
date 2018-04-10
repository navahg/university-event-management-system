package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * EventParticipantsEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "event_participants", schema = "university_event_management_system")
public class EventParticipantsEntity {
    private int idEventParticipants;
    private int idUser;
    private int idEvent;
    private UsersEntity usersByIdUser;
    private EventsEntity eventsByIdEvent;

    @Id
    @Column(name = "id_event_participants", nullable = false)
    public int getIdEventParticipants() {
        return idEventParticipants;
    }

    public void setIdEventParticipants(int idEventParticipants) {
        this.idEventParticipants = idEventParticipants;
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
        EventParticipantsEntity that = (EventParticipantsEntity) o;
        return idEventParticipants == that.idEventParticipants &&
                idUser == that.idUser &&
                idEvent == that.idEvent;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEventParticipants, idUser, idEvent);
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
    @JoinColumn(name = "id_event", referencedColumnName = "id_event", nullable = false)
    public EventsEntity getEventsByIdEvent() {
        return eventsByIdEvent;
    }

    public void setEventsByIdEvent(EventsEntity eventsByIdEvent) {
        this.eventsByIdEvent = eventsByIdEvent;
    }
}
