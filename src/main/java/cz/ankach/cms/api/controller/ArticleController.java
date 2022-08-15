package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateArticleRequest;
import cz.ankach.cms.api.requests.UpdateArticleRequest;
import cz.ankach.cms.api.responses.ArticleResponse;
import cz.ankach.cms.entity.Article;
import cz.ankach.cms.formatters.ArticleFormatter;
import cz.ankach.cms.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController extends AbstractController {

    private final ArticleService articleService;
    private ArticleFormatter articleFormatter;

    public ArticleController(ArticleService articleService, ArticleFormatter articleFormatter) {
        this.articleService = articleService;
        this.articleFormatter = articleFormatter;
    }

    @GetMapping("/articles")
    public List<ArticleResponse> getArticles() {
        var articles = this.articleService.findAll();
        return this.articleFormatter.formatMany(articles);
    }

    @GetMapping("/articles/{articleId}")
    public ArticleResponse getArticle(@PathVariable Long articleId) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Role not found.");
        }
        return this.articleFormatter.format(article.get());
    }

    @PostMapping(path= "/articles", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createRole(
            @RequestBody CreateArticleRequest formRequest
    ) {
        var article = articleService.createArticle(formRequest);
        if (article.isEmpty()) {
            this.sendConflict("Article can not be created.");
        }
        return this.sendCreated("articleId", String.valueOf(article.get().getId()));
    }

    @PatchMapping(path= "/articles/{articleId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRole(
        @RequestBody UpdateArticleRequest formRequest,
        @PathVariable Long articleId
    ) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Article not found");
        }
        articleService.updateArticle(article.get(), formRequest);
    }

    @DeleteMapping(value = "/articles/{articleId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long articleId) {
        var article = this.articleService.findById(articleId);
        if (article.isEmpty()) {
            this.sendNotFound("Article not found");
        }

        this.articleService.deleteArticle(article.get());
    }
}
