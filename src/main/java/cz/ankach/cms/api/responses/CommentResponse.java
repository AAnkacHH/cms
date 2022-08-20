package cz.ankach.cms.api.responses;

import jdk.jshell.execution.LocalExecutionControl;

import java.time.LocalDateTime;

public class CommentResponse {
    public Long commentId;
    public String content;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public String authorName;
    public CommentResponse parentComment;

    public CommentResponse(Long commentId, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String authorName, CommentResponse parentComment) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorName = authorName;
        this.parentComment = parentComment;
    }
}
