package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * EventRequestEntity class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/27/2018
 */
@Entity
@Table(name = "event_request", schema = "university_event_management_system", catalog = "")
public class EventRequestEntity {
    private int idEventRequest;
    private int idEntity;
    private EventsEntity eventsByIdEvent;
    private HierarchyEntity hierarchyByIdHierarchy;

    @Id
    @Column(name = "id_event_request")
    public int getIdEventRequest() {
        return idEventRequest;
    }

    public void setIdEventRequest(int idEventRequest) {
        this.idEventRequest = idEventRequest;
    }

    @Basic
    @Column(name = "id_entity")
    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRequestEntity that = (EventRequestEntity) o;
        return idEventRequest == that.idEventRequest &&
                idEntity == that.idEntity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEventRequest, idEntity);
    }

    @ManyToOne
    @JoinColumn(name = "id_event", referencedColumnName = "id_event", nullable = false)
    public EventsEntity getEventsByIdEvent() {
        return eventsByIdEvent;
    }

    public void setEventsByIdEvent(EventsEntity eventsByIdEvent) {
        this.eventsByIdEvent = eventsByIdEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_hierarchy", referencedColumnName = "id_hierarchy", nullable = false)
    public HierarchyEntity getHierarchyByIdHierarchy() {
        return hierarchyByIdHierarchy;
    }

    public void setHierarchyByIdHierarchy(HierarchyEntity hierarchyByIdHierarchy) {
        this.hierarchyByIdHierarchy = hierarchyByIdHierarchy;
    }
}
