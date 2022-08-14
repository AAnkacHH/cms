package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateArticleRequest;
import cz.ankach.cms.entity.Article;
import cz.ankach.cms.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    public ArticleService(ArticleRepository articleRepository, UserService userService) {
        this.userService = userService;
        this.articleRepository = articleRepository;
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

        Article article = new Article(request.getTitle(), request.getContent(), user.get(), LocalDateTime.now(), LocalDateTime.now());
        this.articleRepository.save(article);
        return Optional.of(article);
    }
}
