package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateCommentRequest;
import cz.ankach.cms.api.requests.UpdateCommentRequest;
import cz.ankach.cms.entity.Article;
import cz.ankach.cms.entity.Comment;
import cz.ankach.cms.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public Optional<Comment> findById(Long id) {
        return this.commentRepository.findById(id);
    }

    public List<Comment> findCommentsByArticle(Article article) {
        return commentRepository.findByArticle(article);
    }

    public Optional<Comment> createComment(CreateCommentRequest request, Article article) {
        if (request.authorId == null) {
            return Optional.empty();
        }

        Comment comment = new Comment();
        comment.setContent(request.content);
        comment.setArticle(article);
        if (request.parentCommentId != null) {
            var parent = this.findById(request.parentCommentId);
            parent.ifPresent(comment::setParent);
        }
        var author = this.userService.getById(request.authorId);
        author.ifPresent(comment::setAuthor);
        this.commentRepository.save(comment);

        return Optional.of(comment);
    }

    public boolean updateComment(UpdateCommentRequest formRequest, Comment comment, Article article) {
        comment.setContent(formRequest.getContent());
        comment.setUpdatedAt();
        if (!Objects.equals(article.getId(), comment.getArticle().getId())) {
            return false;
        }

        commentRepository.save(comment);
        return true;
    }



    public void removeByArticle(Article article) {
        this.commentRepository.removeByArticle(article);
    }

    public boolean deleteComment(Comment comment, Article article) {
        if (!Objects.equals(article.getId(), comment.getArticle().getId())) {
            return false;
        }
        comment.setParent(null);
        article.deleteComment(comment);
        commentRepository.delete(comment);
        commentRepository.flush();
        return true;
    }
}
