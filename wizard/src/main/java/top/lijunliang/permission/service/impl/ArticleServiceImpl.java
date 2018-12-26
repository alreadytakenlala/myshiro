package top.lijunliang.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lijunliang.permission.dao.ArticleDao;
import top.lijunliang.permission.dao.MenuDao;
import top.lijunliang.permission.entity.Article;
import top.lijunliang.permission.entity.Menu;
import top.lijunliang.permission.service.ArticleService;
import top.lijunliang.permission.utils.CodeUtil;
import top.lijunliang.permission.utils.StatusCode;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService
{
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Article> selectAll()
    {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }

    @Override
    public StatusCode addArticle(Article article)
    {
        menuDao.add(new Menu(CodeUtil.createRandomNumber(), "添加", "article:add"));
        articleDao.add(article);
        return new StatusCode(2000, "添加文章成功");
    }

    @Override
    public void updateArticle(Article article)
    {
        menuDao.add(new Menu(CodeUtil.createRandomNumber(),"更新", "article:update"));
        articleDao.update(article);
    }

    @Override
    public StatusCode deleteArticle(Article article)
    {
        menuDao.add(new Menu(CodeUtil.createRandomNumber(),"删除", "article:delete"));
        articleDao.delete(article);
        return new StatusCode(2000, "删除文章成功");
    }

    @Override
    public Article findByArticleId(Article article)
    {
        return articleDao.findByArticleId(article);
    }
}
