package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * HierarchyEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "hierarchy", schema = "university_event_management_system", catalog = "")
public class HierarchyEntity {
    private int idHierarchy;
    private int level;
    private String tableName;
    private String description;
    private Collection<ClubsEntity> clubsByIdHierarchy;
    private Collection<EventsEntity> eventsByIdHierarchy;
    private Collection<EventRequestEntity> eventRequestsByIdHierarchy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hierarchy", nullable = false)
    public int getIdHierarchy() {
        return idHierarchy;
    }

    public void setIdHierarchy(int idHierarchy) {
        this.idHierarchy = idHierarchy;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "table_name", nullable = false, length = 255)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HierarchyEntity that = (HierarchyEntity) o;
        return idHierarchy == that.idHierarchy &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idHierarchy, tableName, description);
    }

    @OneToMany(mappedBy = "hierarchyByIdHierarchy")
    public Collection<ClubsEntity> getClubsByIdHierarchy() {
        return clubsByIdHierarchy;
    }

    public void setClubsByIdHierarchy(Collection<ClubsEntity> clubsByIdHierarchy) {
        this.clubsByIdHierarchy = clubsByIdHierarchy;
    }

    @OneToMany(mappedBy = "hierarchyByIdHierarchy")
    public Collection<EventsEntity> getEventsByIdHierarchy() {
        return eventsByIdHierarchy;
    }

    public void setEventsByIdHierarchy(Collection<EventsEntity> eventsByIdHierarchy) {
        this.eventsByIdHierarchy = eventsByIdHierarchy;
    }

    @OneToMany(mappedBy = "hierarchyByIdHierarchy")
    public Collection<EventRequestEntity> getEventRequestsByIdHierarchy() {
        return eventRequestsByIdHierarchy;
    }

    public void setEventRequestsByIdHierarchy(Collection<EventRequestEntity> eventRequestsByIdHierarchy) {
        this.eventRequestsByIdHierarchy = eventRequestsByIdHierarchy;
    }
}
