package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import kr.article.dao.ArticleContentDao;
import kr.article.dao.ArticleDao;
import kr.article.dao.CommentDao;
import kr.article.model.Article;
import kr.s2b.jdbc.connection.JDBCUtil;
import kr.s2b.jdbc.connection.ConnectionProvider;

public class DeleteArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	private CommentDao commentDao = new CommentDao();
	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, 
					delReq.getArticleNum());
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			if (!candelete(delReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			articleDao.delete(conn, 
					delReq.getArticleNum());
			contentDao.delete(conn, 
					delReq.getArticleNum());
			commentDao.delete(conn, delReq.getArticleNum());
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

	private boolean candelete(String deleteUserId, Article article) {
		return article.getWriter().getId().equals(deleteUserId);
	}
}
