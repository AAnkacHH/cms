package cz.ankach.cms.entity;

import java.util.Date;
import java.util.List;

public class Post {

    private static long idGenerator = 1;
    private final long id;

    private String title;

    private String content;

    private Post parent = null;

    private int order = 1;  // needed for ordering child posts.

    private User author;

    private Date createdAt;

    private Date updatedAt;

    private List<Comment> comments;

    public Post(String title, String content, User author, Date createdAt, Date updatedAt) {
        this.id = idGenerator++;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
