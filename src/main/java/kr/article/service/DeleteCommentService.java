package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import kr.article.dao.CommentDao;
import kr.article.model.Comment;
import kr.s2b.jdbc.connection.ConnectionProvider;
import kr.s2b.jdbc.connection.JDBCUtil;

public class DeleteCommentService {

	private CommentDao commentDao = new CommentDao();
	
	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment comment = commentDao.select(conn, delReq.getArticleNum());
			if(comment == null) {
				throw new RuntimeException();
			}
			if(!candelete(delReq.getUserId(), comment)) {
				throw new RuntimeException();
			}
			
			commentDao.delete(conn, delReq.getArticleNum());
			conn.commit();
		}catch(SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e) ;
		}finally {
			JDBCUtil.close(conn);
		}
	}
	
	private boolean candelete(String deleteUserId, Comment comment) {
		return comment.getWriter().getId().equals(deleteUserId);
	}
}
