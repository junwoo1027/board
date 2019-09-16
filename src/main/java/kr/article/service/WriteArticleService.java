package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import kr.article.dao.ArticleContentDao;
import kr.article.dao.ArticleDao;
import kr.article.model.Article;
import kr.article.model.ArticleContent;
import kr.s2b.jdbc.connection.JDBCUtil;
import kr.s2b.jdbc.connection.ConnectionProvider;

public class WriteArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Article article = toArticle(req);
			Article savedArticle = articleDao.insert(conn, article);
			if (savedArticle == null) {
				throw new RuntimeException("fail to insert article");
			}
			ArticleContent content = new ArticleContent(
					savedArticle.getNumber(),
					req.getContent());
			ArticleContent savedContent = contentDao.insert(conn, content);
			if (savedContent == null) {
				throw new RuntimeException("fail to insert content");
			}

			conn.commit();

			return savedArticle.getNumber();
		} catch (SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JDBCUtil.rollback(conn);
			throw e;
		} finally {
			JDBCUtil.close(conn);
		}
	}

	private Article toArticle(WriteRequest req) {
		Date now = new Date();
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}
