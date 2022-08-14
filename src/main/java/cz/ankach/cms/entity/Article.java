package cz.ankach.cms.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "article")
public class Article {

    @Id
    @SequenceGenerator(name = "article_id_gen", sequenceName = "article_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_gen")
    @Column(name = "article_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "article_order")
    private Integer articleOrder = 1;  // needed for ordering child posts.

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name="fk_article_author"))
    private User author;

    @ManyToOne
    @JoinColumn(name = "parent_article_id", foreignKey = @ForeignKey(name = "fk_article_parent"))
    private Article parentArticle = null;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Article() {}

    public Article(String title, String content, User author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Article getParent() {
        return parentArticle;
    }

    public void setParent(Article parent) {
        this.parentArticle = parent;
    }

    public Integer getOrder() {
        return articleOrder;
    }

    public void setOrder(Integer order) {
        this.articleOrder = order;
    }
}
