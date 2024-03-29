package cz.ankach.cms.repository;

import cz.ankach.cms.entity.Article;
import cz.ankach.cms.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Transactional
    public void removeByArticle(Article article);

    public List<Comment> findByArticle(Article article);
}
