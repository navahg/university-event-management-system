package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * EventStatusEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "event_status", schema = "university_event_management_system", catalog = "")
public class EventStatusEntity {
    private int idStatus;
    private String statusMessage;
    private Collection<EventsEntity> eventsByIdStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status", nullable = false)
    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    @Basic
    @Column(name = "status_message", nullable = false, length = 255)
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventStatusEntity that = (EventStatusEntity) o;
        return idStatus == that.idStatus &&
                Objects.equals(statusMessage, that.statusMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStatus, statusMessage);
    }

    @OneToMany(mappedBy = "eventStatusByStatus")
    public Collection<EventsEntity> getEventsByIdStatus() {
        return eventsByIdStatus;
    }

    public void setEventsByIdStatus(Collection<EventsEntity> eventsByIdStatus) {
        this.eventsByIdStatus = eventsByIdStatus;
    }
}
