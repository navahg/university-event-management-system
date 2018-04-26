package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * NotificationsEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "notifications", schema = "university_event_management_system")
public class NotificationsEntity {
    private int idNotification;
    private String message;
    private boolean readFlag;
    private UsersEntity usersByIdUser;
    private EventsEntity eventsByIdEvent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification", nullable = false)
    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    @Basic
    @Column(name = "message", nullable = false, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "read_flag", nullable = false)
    public boolean isReadFlag() {
        return readFlag;
    }

    public void setReadFlag(boolean readFlag) {
        this.readFlag = readFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationsEntity that = (NotificationsEntity) o;
        return idNotification == that.idNotification &&
                usersByIdUser.equals(that.getUsersByIdUser()) &&
                readFlag == that.readFlag &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idNotification, usersByIdUser.getIdUser(), message, readFlag);
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
