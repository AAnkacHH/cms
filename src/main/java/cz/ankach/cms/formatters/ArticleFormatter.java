package cz.ankach.cms.formatters;

import cz.ankach.cms.api.responses.ArticleResponse;
import cz.ankach.cms.api.responses.CommentResponse;
import cz.ankach.cms.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleFormatter {
    private final UserFormatter userFormatter;
    private final CommentFormatter commentFormatter;

    public ArticleFormatter(UserFormatter userFormatter, CommentFormatter commentFormatter) {
        this.userFormatter = userFormatter;
        this.commentFormatter = commentFormatter;
    }

    /**
     * Method formats an Article object as response object.
     * If an article has parent one it formats parent too.
     * @return ArticleResponse
     * */
    public ArticleResponse format(Article article) {
        List<CommentResponse> comments = article.getComments().stream().map(this.commentFormatter::format).toList();
        var userResponse = this.userFormatter.format(article.getAuthor());
        var parent = article.getParent();

        return new ArticleResponse(
                article.getTitle(),
                article.getContent(),
                userResponse,
                article.getOrder(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                parent != null ? this.format(parent) : null,
                comments
        );
    }

    /**
     * Method formats a list of articles as response object.
     * */
    public List<ArticleResponse> formatMany(List<Article>  articles) {
        return articles.stream().map(this::format).toList();
    }
}
