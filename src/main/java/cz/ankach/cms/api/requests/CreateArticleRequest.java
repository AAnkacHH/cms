package cz.ankach.cms.api.requests;

/**
 * Request which represents json body in http request for Article object creation.
 * */
public class CreateArticleRequest {
    public String title;
    public String content;
    public Long articleId;
    public Long userId;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getUserId() {
        return userId;
    }
}
