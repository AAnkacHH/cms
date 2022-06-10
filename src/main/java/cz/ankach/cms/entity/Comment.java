package cz.ankach.cms.entity;

import java.util.Date;

public class Comment {

    private static long idGenerator = 1;
    private final long id;

    private Comment parent;

    private String content;

    private User author;

    private Date createdAt;

    public Comment(Comment parent, String content, User author, Date createdAt) {
        this.id = idGenerator++;
        this.parent = parent;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }
}
