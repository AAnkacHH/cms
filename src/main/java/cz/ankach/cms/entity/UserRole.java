package cz.ankach.cms.entity;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @SequenceGenerator(name = "user_role_id_gen", sequenceName = "user_role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_role_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_user_role_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name="fk_user_role_role"))
    private Role role;

    @Column(name = "added_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime addedAt;

    public UserRole() {}

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        this.addedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
