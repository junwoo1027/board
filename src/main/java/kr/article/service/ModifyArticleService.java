package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import kr.article.dao.ArticleContentDao;
import kr.article.dao.ArticleDao;
import kr.article.model.Article;
import kr.s2b.jdbc.connection.JDBCUtil;
import kr.s2b.jdbc.connection.ConnectionProvider;

public class ModifyArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, 
					modReq.getArticleNumber());
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			if (!canModify(modReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			articleDao.update(conn, 
					modReq.getArticleNumber(), modReq.getTitle());
			contentDao.update(conn, 
					modReq.getArticleNumber(), modReq.getContent());
			conn.commit();
		} catch (SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JDBCUtil.rollback(conn);
			throw e;
		} finally {
			JDBCUtil.close(conn);
		}
	}

	private boolean canModify(String modfyingUserId, Article article) {
		return article.getWriter().getId().equals(modfyingUserId);
	}
}
