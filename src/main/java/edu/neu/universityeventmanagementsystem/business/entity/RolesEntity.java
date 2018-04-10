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
    private Collection<UsersEntity> usersByIdRole;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesEntity that = (RolesEntity) o;
        return idRole == that.idRole &&
                privilegeLevel == that.privilegeLevel &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRole, name, privilegeLevel);
    }

    @OneToMany(mappedBy = "rolesByIdRole")
    public Collection<UsersEntity> getUsersByIdRole() {
        return usersByIdRole;
    }

    public void setUsersByIdRole(Collection<UsersEntity> usersByIdRole) {
        this.usersByIdRole = usersByIdRole;
    }
}
