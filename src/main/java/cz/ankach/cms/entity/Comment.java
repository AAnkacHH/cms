package cz.ankach.cms.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_id_gen", sequenceName = "comment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", foreignKey = @ForeignKey(name = "fk_comment_parent"))
    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_comment_article"))
    private Article article;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name="fk_comment_author"))
    private User author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment(Article article, String content, User author, Comment parent) {
        this.parent = parent;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.article = article;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Article getArticle() {
        return article;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
