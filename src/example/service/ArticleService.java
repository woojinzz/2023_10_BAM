package example.service;

import java.util.List;

import example.Container;
import example.dao.ArticleDao;
import example.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public int getLastId() {
	
		return articleDao.getLastId();
	}

	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		// TODO Auto-generated method stub
		return articleDao.getArticleById(id);
	}

	public void doDelete(Article foundArticle) {
		// TODO Auto-generated method stub
		articleDao.doDelete(foundArticle);
	}

	public void doModify(Article foundArticle, String title, String body) {
		// TODO Auto-generated method stub
		articleDao.doModify(foundArticle, title, body);
	}

	public void doWrite(Article article) {
		// TODO Auto-generated method stub
		articleDao.doWrite(article);
	}


	

}
