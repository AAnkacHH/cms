package cz.ankach.cms.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tag_group")
public class TagGroup {
    @Id
    @SequenceGenerator(name = "group_id_gen", sequenceName = "tag_group_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_gen")
    @Column(name = "group_id")
    private Long id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "private")
    private boolean isPrivate = false;

    private boolean disjoint = false;

    public TagGroup() {}

    public TagGroup(String name, boolean isPrivate, boolean disjoint, String description) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.disjoint = disjoint;
    }

    public Long getId() {
        return id;
    }
}
