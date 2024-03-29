package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateArticleRequest;
import cz.ankach.cms.api.requests.UpdateArticleRequest;
import cz.ankach.cms.entity.Article;
import cz.ankach.cms.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    private final CommentService commentService;

    public ArticleService(ArticleRepository articleRepository, UserService userService, CommentService commentService) {
        this.userService = userService;
        this.articleRepository = articleRepository;
        this.commentService = commentService;
    }

    /**
     * Method finds an article by its identifier.
     * */
    public Optional<Article> findById(Long articleId) {
        return this.articleRepository.findById(articleId);
    }

    /**
     * Method finds all articles in system.
     * */
    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }


    /**
     * Method creates an Article object from article request.
     * */
    public Optional<Article> createArticle(CreateArticleRequest request) {
        var user = this.userService.getById(request.userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        if (request.getArticleId() != null) {
            var parentArticle = this.findById(request.articleId);
            if (parentArticle.isEmpty()) {
                return Optional.empty();
            }
        }

        Article article = new Article(request.getTitle(), request.getContent(), user.get());
        this.articleRepository.save(article);
        return Optional.of(article);
    }

    public void updateArticle(Article article, UpdateArticleRequest request) {
        if (request.title != null) {
            article.setTitle(request.title);
        }
        if (request.content != null) {
            article.setContent(request.content);
        }
        articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        this.commentService.removeByArticle(article);
        articleRepository.deleteById(article.getId());
    }
}
