package cz.ankach.cms.entity;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "role", uniqueConstraints = {
      @UniqueConstraint(name = "udx_role_name", columnNames = {"name"})
})
public class Role {
    @Id
    @SequenceGenerator(name = "role_id_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "role_id_gen")
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }
}
