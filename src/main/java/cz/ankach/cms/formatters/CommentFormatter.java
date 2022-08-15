package cz.ankach.cms.formatters;

import cz.ankach.cms.api.responses.CommentResponse;
import cz.ankach.cms.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentFormatter {
    public CommentResponse format(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getAuthor().getUsername(),
                comment.getParent() != null ? this.format(comment.getParent()) : null
        );
    }
}
