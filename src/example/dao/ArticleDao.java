package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.dto.Article;

public class ArticleDao extends Dao {
	private List<Article> articles;// 게시글 저장
	
	
	public ArticleDao() {
		this.articles =  new ArrayList<>(); // 아티클스 초기화
		
	}
	

	public void doWrite(Article article) {
		this.articles.add(article);
		this.lastId++;
	}

	public List<Article> getArticles() {
		return this.articles;
	}
	
	public void doModify(Article foundArticle, String title, String body) {
		foundArticle.title = title;
		foundArticle.body = body;
	}
	
	public void doDelete(Article foundArticle) {
		this.articles.remove(foundArticle);
		
	}
	
	public Article getArticleById(int id) {// id 값 넘겨 받음
		
		for (Article article : this.articles) { // 아디클을 넘겨주기 위해 존재
			if (article.id == id) {
				return article;
			} 
		}
		return null;
	}

	




	
}
