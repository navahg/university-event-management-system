package edu.neu.universityeventmanagementsystem.business.entity;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

/**
 * EventsEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "events", schema = "university_event_management_system")
public class EventsEntity {
    private int idEvent;
    private int idEntity;
    private String name;
    private String venue;
    private Timestamp startTime;
    private Timestamp endTime;
    private int maxSeats;
    private Collection<EventParticipantsEntity> eventParticipantsByIdEvent;
    private HierarchyEntity hierarchyByIdHierarchy;
    private UsersEntity usersByIdCreator;
    private EventStatusEntity eventStatusByStatus;
    private Collection<InvitesEntity> invitesByIdEvent;
    private Collection<SchedulesEntity> schedulesByIdEvent;
    private Collection<NotificationsEntity> notificationsByIdEvent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event", nullable = false)
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Basic
    @Column(name = "id_entity")
    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "venue", nullable = false, length = 255)
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Basic
    @Column(name = "start_time", nullable = false)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = false)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "max_seats")
    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventsEntity that = (EventsEntity) o;
        return idEvent == that.idEvent &&
                hierarchyByIdHierarchy.equals(that.getHierarchyByIdHierarchy()) &&
                usersByIdCreator.equals(that.getUsersByIdCreator()) &&
                eventStatusByStatus.equals(that.getEventStatusByStatus()) &&
                Objects.equals(name, that.name) &&
                Objects.equals(venue, that.venue) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEvent, hierarchyByIdHierarchy.getIdHierarchy(), usersByIdCreator.getIdUser(),
                name, venue, startTime, endTime, eventStatusByStatus.getIdStatus());
    }

    @OneToMany(mappedBy = "eventsByIdEvent")
    public Collection<EventParticipantsEntity> getEventParticipantsByIdEvent() {
        return eventParticipantsByIdEvent;
    }

    public void setEventParticipantsByIdEvent(Collection<EventParticipantsEntity> eventParticipantsByIdEvent) {
        this.eventParticipantsByIdEvent = eventParticipantsByIdEvent;
    }

    @ManyToOne
    @JoinColumn(name = "id_hierarchy", referencedColumnName = "id_hierarchy", nullable = false)
    public HierarchyEntity getHierarchyByIdHierarchy() {
        return hierarchyByIdHierarchy;
    }

    public void setHierarchyByIdHierarchy(HierarchyEntity hierarchyByIdHierarchy) {
        this.hierarchyByIdHierarchy = hierarchyByIdHierarchy;
    }

    @ManyToOne
    @JoinColumn(name = "id_creator", referencedColumnName = "id_user", nullable = false)
    public UsersEntity getUsersByIdCreator() {
        return usersByIdCreator;
    }

    public void setUsersByIdCreator(UsersEntity usersByIdCreator) {
        this.usersByIdCreator = usersByIdCreator;
    }

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id_status", nullable = false)
    public EventStatusEntity getEventStatusByStatus() {
        return eventStatusByStatus;
    }

    public void setEventStatusByStatus(EventStatusEntity eventStatusByStatus) {
        this.eventStatusByStatus = eventStatusByStatus;
    }

    @OneToMany(mappedBy = "eventsByIdEvent")
    public Collection<InvitesEntity> getInvitesByIdEvent() {
        return invitesByIdEvent;
    }

    public void setInvitesByIdEvent(Collection<InvitesEntity> invitesByIdEvent) {
        this.invitesByIdEvent = invitesByIdEvent;
    }

    @OneToMany(mappedBy = "eventsByIdEvent")
    public Collection<SchedulesEntity> getSchedulesByIdEvent() {
        return schedulesByIdEvent;
    }

    public void setSchedulesByIdEvent(Collection<SchedulesEntity> schedulesByIdEvent) {
        this.schedulesByIdEvent = schedulesByIdEvent;
    }

    @OneToMany(mappedBy = "eventsByIdEvent")
    public Collection<NotificationsEntity> getNotificationsByIdEvent() {
        return notificationsByIdEvent;
    }

    public void setNotificationsByIdEvent(Collection<NotificationsEntity> notificationsByIdEvent) {
        this.notificationsByIdEvent = notificationsByIdEvent;
    }
}
