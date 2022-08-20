package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateCommentRequest;
import cz.ankach.cms.api.requests.UpdateCommentRequest;
import cz.ankach.cms.api.responses.CommentResponse;
import cz.ankach.cms.entity.Comment;
import cz.ankach.cms.formatters.ArticleFormatter;
import cz.ankach.cms.formatters.CommentFormatter;
import cz.ankach.cms.service.ArticleService;
import cz.ankach.cms.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController extends AbstractController {

    private final ArticleService articleService;

    private final CommentService commentService;
    private final CommentFormatter commentFormatter;

    public CommentController(ArticleService articleService, CommentService commentService, CommentFormatter commentFormatter) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.commentFormatter = commentFormatter;
    }

    @GetMapping(value = "/articles/{articleId}/comments")
    public List<CommentResponse> getCommentsByArticle(@PathVariable Long articleId) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Article not found");
        }
        List<Comment> comments = this.commentService.findCommentsByArticle(article.get());

        return this.commentFormatter.formatMany(comments);
    }

    @PostMapping(value = "/articles/{articleId}/comments")
    public ResponseEntity<Object> createComment(
            @RequestBody CreateCommentRequest formRequest,
            @PathVariable Long articleId
    ) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Article not found");
        }

        var comment = this.commentService.createComment(formRequest, article.get());
        if (comment.isEmpty()) {
            this.sendConflict("");
        }
        return this.sendCreated("commentId", String.valueOf(comment.get().getId()));
    }

    @PutMapping(value = "/articles/{articleId}/comments/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateComment(
            @RequestBody UpdateCommentRequest formRequest,
            @PathVariable Long articleId,
            @PathVariable Long commentId
    ) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Article not found");
        }

        var comment = this.commentService.findById(commentId);
        if (comment.isEmpty()) {
            this.sendNotFound("Article not found");
        }

        var res = this.commentService.updateComment(formRequest, comment.get(), article.get());
        if (!res) {
            this.sendConflict("Comment is not corresponding to article.");
        }
    }

    @DeleteMapping(value = "/articles/{articleId}/comments/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable Long articleId,
            @PathVariable Long commentId
    ) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Article not found");
        }

        var comment = this.commentService.findById(commentId);
        if (comment.isEmpty()) {
            this.sendNotFound("Article not found");
        }

        var res = this.commentService.deleteComment(comment.get(), article.get());
        if (!res) {
            this.sendConflict("Comment is not corresponding to article.");
        }
    }
}
