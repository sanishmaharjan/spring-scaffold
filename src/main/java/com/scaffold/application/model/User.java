package com.scaffold.application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = User.FIND_ALL_QUERY),
        @NamedQuery(name = User.FIND_BY_USER_NAME, query = User.FIND_BY_USER_NAME_QUERY),
        @NamedQuery(name = User.IS_ACTIVE_USER, query = User.IS_ACTIVE_USER_QUERY),
        @NamedQuery(name = User.FIND_BY_PERSON, query = User.FIND_BY_PERSON_QUERY),
        @NamedQuery(name = User.FIND_All_BY_ROLE, query = User.FIND_All_BY_ROLE_QUERY)
})
public class User implements Serializable {

    private static final long serialVersionUID = -101741509725795615L;
    public static final String PREFIX = "user.";
    public static final String FIND_ALL = PREFIX + "findAll";
    public static final String FIND_ALL_QUERY = "SELECT u FROM User u where u.isDeleted=false";
    public static final String FIND_BY_USER_NAME = PREFIX + "findByUserName";
    public static final String FIND_BY_USER_NAME_QUERY = "SELECT u FROM User u where u.userName=:userName";
    public static final String IS_ACTIVE_USER = PREFIX + "isActiveUser";
    public static final String IS_ACTIVE_USER_QUERY = "SELECT u FROM User u where u.userName=:userName and u.isActive=true";
    public static final String FIND_BY_PERSON = PREFIX + "findByPerson";
    public static final String FIND_BY_PERSON_QUERY = "SELECT u FROM User u where u.person=:person and u.isDeleted=false";
    public static final String FIND_All_BY_ROLE = PREFIX + "findAllByRole";
    public static final String FIND_All_BY_ROLE_QUERY = "SELECT u FROM User u where u.role=:role and u.isDeleted=false";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "delete_reason")
    private String deleteReason;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deleteDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public User() {
    }

    public User(Person person, Role role, String userName, String password, String salt, Boolean isActive) {
        this.person = person;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.isActive = isActive;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", personId=" + person.getId() +
                ", roleId=" + role.getId() +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", isDeleted=" + isDeleted +
                ", deleteReason='" + deleteReason + '\'' +
                '}';
    }
}

