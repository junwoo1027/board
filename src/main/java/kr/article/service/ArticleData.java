package kr.article.service;

import kr.article.model.Article;
import kr.article.model.ArticleContent;

public class ArticleData {

	private Article article;
	private ArticleContent content;
	
	public ArticleData(Article article, ArticleContent content) {
		this.article=article;
		this.content=content;
	}

	public Article getArticle() {
		return article;
	}

	public String getContent() {
		return content.getContent();
	}
}
