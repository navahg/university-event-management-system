package edu.neu.universityeventmanagementsystem.business.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * RolesEntity class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/9/18
 */
@Entity
@Table(name = "roles", schema = "university_event_management_system")
public class RolesEntity {
    private int idRole;
    private String name;
    private int privilegeLevel;
    private int idEntity;
    private HierarchyEntity hierarchyByIdHierarchy;
    private Collection<UsersEntity> usersByIdRole;

    public final static int SYSTEM_ADMIN = 99;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false)
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
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
    @Column(name = "privilege_level", nullable = false)
    public int getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(int privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
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
        RolesEntity that = (RolesEntity) o;
        return idRole == that.idRole &&
                privilegeLevel == that.privilegeLevel &&
                getHierarchyByIdHierarchy().getIdHierarchy() == that.getHierarchyByIdHierarchy().getIdHierarchy() &&
                idEntity == that.idEntity &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRole, name, privilegeLevel, getHierarchyByIdHierarchy().getIdHierarchy(), idEntity);
    }

    @ManyToOne
    @JoinColumn(name = "id_hierarchy", referencedColumnName = "id_hierarchy", nullable = false)
    public HierarchyEntity getHierarchyByIdHierarchy() {
        return hierarchyByIdHierarchy;
    }

    public void setHierarchyByIdHierarchy(HierarchyEntity hierarchyByIdHierarchy) {
        this.hierarchyByIdHierarchy = hierarchyByIdHierarchy;
    }

    @OneToMany(mappedBy = "rolesByIdRole")
    public Collection<UsersEntity> getUsersByIdRole() {
        return usersByIdRole;
    }

    public void setUsersByIdRole(Collection<UsersEntity> usersByIdRole) {
        this.usersByIdRole = usersByIdRole;
    }
}
