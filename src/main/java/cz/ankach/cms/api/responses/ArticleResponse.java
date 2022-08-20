package cz.ankach.cms.api.responses;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents json payload in http response for article objects.
 * */
public class ArticleResponse {
    public String title;
    public String content;
    public UserResponse user;
    public Integer order;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public ArticleResponse parentArticle;

    public List<CommentResponse> comments;

    public ArticleResponse(
            String title,
            String content,
            UserResponse user,
            Integer order,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            ArticleResponse parentArticle,
            List<CommentResponse> comments
    ) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.order = order;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.parentArticle = parentArticle;
        this.comments = comments;
    }
}
