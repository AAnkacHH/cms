package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateCommentRequest;
import cz.ankach.cms.entity.Article;
import cz.ankach.cms.entity.Comment;
import cz.ankach.cms.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public Optional<Comment> getById(Long id) {
        return this.commentRepository.findById(id);
    }

    public Optional<Comment> createComment(CreateCommentRequest request, Article article) {
        if (request.authorId == null) {
            return Optional.empty();
        }

        Comment comment = new Comment();
        comment.setContent(request.content);
        comment.setArticle(article);
        if (request.parentCommentId != null) {
            var parent = this.getById(request.parentCommentId);
            parent.ifPresent(comment::setParent);
        }
        var author = this.userService.getById(request.authorId);
        author.ifPresent(comment::setAuthor);
        this.commentRepository.save(comment);

        return Optional.of(comment);
    }



    public List<Comment> removeByArticle(Article article) {
        return this.commentRepository.removeByArticle(article);
    }
}
