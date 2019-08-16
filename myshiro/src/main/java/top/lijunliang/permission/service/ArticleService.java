package top.lijunliang.permission.service;

import top.lijunliang.permission.entity.Article;
import top.lijunliang.permission.utils.StatusCode;

import java.util.List;

public interface ArticleService
{
    List<Article> selectAll();

    public StatusCode addArticle(Article article);

    public void updateArticle(Article article);

    public StatusCode deleteArticle(Article article);

    public Article findByArticleId(Article article);
}
