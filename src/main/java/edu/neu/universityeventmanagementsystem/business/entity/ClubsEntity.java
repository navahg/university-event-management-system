package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * ClubsEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "clubs", schema = "university_event_management_system")
public class ClubsEntity {
    private int idClub;
    private String name;
    private Collection<ClubMembersEntity> clubMembersByIdClub;
    private HierarchyEntity hierarchyByIdHierarchy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club", nullable = false)
    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubsEntity that = (ClubsEntity) o;
        return idClub == that.idClub &&
                hierarchyByIdHierarchy.equals(that.getHierarchyByIdHierarchy()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idClub, hierarchyByIdHierarchy.getIdHierarchy(), name);
    }

    @OneToMany(mappedBy = "clubsByIdClub")
    public Collection<ClubMembersEntity> getClubMembersByIdClub() {
        return clubMembersByIdClub;
    }

    public void setClubMembersByIdClub(Collection<ClubMembersEntity> clubMembersByIdClub) {
        this.clubMembersByIdClub = clubMembersByIdClub;
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
