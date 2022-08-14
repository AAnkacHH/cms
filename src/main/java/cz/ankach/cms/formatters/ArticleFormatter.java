package cz.ankach.cms.formatters;

import cz.ankach.cms.api.responses.ArticleResponse;
import cz.ankach.cms.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleFormatter {
    private final UserFormatter userFormatter;

    public ArticleFormatter(UserFormatter userFormatter) {
        this.userFormatter = userFormatter;
    }

    public ArticleResponse format(Article article) {

        var userResponse = this.userFormatter.format(article.getAuthor());
        if (article.getParent() != null) {
            return new ArticleResponse(
                    article.getTitle(),
                    article.getContent(),
                    userResponse,
                    article.getOrder(),
                    article.getCreatedAt(),
                    article.getUpdatedAt(),
                    this.format(article.getParent())
            );
        }
        return new ArticleResponse(
                article.getTitle(),
                article.getContent(),
                userResponse,
                article.getOrder(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }

    public List<ArticleResponse> formatMany(List<Article>  articles) {
        return articles.stream().map(this::format).toList();
    }
}
