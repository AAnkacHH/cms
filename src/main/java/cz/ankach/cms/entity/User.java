package cz.ankach.cms.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_id_gen")
    @Column(name = "user_id", updatable = false)
    private Long id;

    private String firstname;
    private String lastname;
    private String username;

    @OneToMany(mappedBy = "user")
    Set<UserRole> roles = new HashSet<>();

    public User () {
    }

    public User(String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }
}
