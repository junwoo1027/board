package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import kr.article.dao.ArticleDao;
import kr.article.dao.CommentDao;
import kr.article.service.CommentRequest;

import kr.article.model.Article;
import kr.article.model.Comment;
import kr.s2b.jdbc.connection.ConnectionProvider;
import kr.s2b.jdbc.connection.JDBCUtil;

public class WriteCommentService {

	private CommentDao commentDao = new CommentDao();
	private ArticleDao articleDao = new ArticleDao();
	
	public Integer write(CommentRequest comReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn,
					comReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			Comment comment = toComment(comReq);
			
			Comment savedComment =commentDao.insert(conn,
					comReq.getArticleNumber(), comment);
			if(savedComment == null) {
				throw new RuntimeException("fail comment");
			}
			conn.commit();
			
			return savedComment.getNumber();
		}catch(SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			JDBCUtil.rollback(conn);
			throw e;
		}finally {
			JDBCUtil.close(conn);
		}
		
	}
	
	private Comment toComment(CommentRequest comReq) {
		return new Comment(null, comReq.getArticleNumber(), comReq.getWriter(), comReq.getContent());
	}
}
