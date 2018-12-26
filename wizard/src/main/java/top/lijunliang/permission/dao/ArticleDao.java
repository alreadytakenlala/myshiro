package top.lijunliang.permission.dao;

import org.springframework.stereotype.Repository;
import top.lijunliang.permission.entity.Article;

import java.util.List;

@Repository
public interface ArticleDao
{
    void add(Article article);

    void delete(Article article);

    void update(Article article);

    Article findByArticleId(Article article);

    List<Article> selectAll();
}
