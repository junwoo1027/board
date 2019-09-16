package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.article.dao.ArticleDao;
import kr.article.model.Article;
import kr.s2b.jdbc.connection.ConnectionProvider;

public class ListArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = articleDao.selectCount(conn);
			List<Article> content = articleDao.select(conn, (pageNum - 1) * size, size);
			return new ArticlePage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
