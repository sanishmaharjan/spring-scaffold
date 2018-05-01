package com.scaffold.application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = Role.FIND_BY_ROLE, query = Role.FIND_BY_ROLE_QUERY),
        @NamedQuery(name = Role.FIND_ALL, query = Role.FIND_ALL_QUERY),
})
public class Role implements Serializable {

    private static final long serialVersionUID = -6849355756204772720L;
    public static final String PREFIX = "role.";
    public static final String FIND_ALL = PREFIX + "findAll";
    public static final String FIND_ALL_QUERY = "SELECT r FROM Role r";
    public static final String FIND_BY_ROLE = PREFIX + "findByRole";
    public static final String FIND_BY_ROLE_QUERY = "SELECT r FROM Role r where r.role=:userRole";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
