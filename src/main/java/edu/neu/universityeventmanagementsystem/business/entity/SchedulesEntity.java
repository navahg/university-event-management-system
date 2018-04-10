package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * SchedulesEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "schedules", schema = "university_event_management_system")
public class SchedulesEntity {
    private int idSchedule;
    private int idUser;
    private int idEvent;
    private UsersEntity usersByIdUser;
    private EventsEntity eventsByIdEvent;

    @Id
    @Column(name = "id_schedule", nullable = false)
    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
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
        SchedulesEntity that = (SchedulesEntity) o;
        return idSchedule == that.idSchedule &&
                idUser == that.idUser &&
                idEvent == that.idEvent;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSchedule, idUser, idEvent);
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
