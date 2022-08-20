package cz.ankach.cms.api.requests;

public class CreateCommentRequest {
    public Long parentCommentId;
    public Long authorId;

    public String content;
}
