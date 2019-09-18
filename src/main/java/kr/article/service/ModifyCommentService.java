package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.article.model.Comment;
import kr.article.dao.CommentDao;
import kr.s2b.jdbc.connection.ConnectionProvider;
import kr.s2b.jdbc.connection.JDBCUtil;

public class ModifyCommentService {

	private CommentDao commentDao = new CommentDao();
	
	public void modify(ModifyCommentRequest modReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			
			List<Comment> comment = commentDao.selectById(conn, modReq.getCommentNumber());
			if(comment == null) {
				throw new RuntimeException();
			}
//			if(!canModify(modReq.getUserId(), comment)) {
//				throw new RuntimeException();
//			}
			
			commentDao.update(conn, modReq.getCommentNumber(), modReq.getContent());
			conn.commit();
		}catch(SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JDBCUtil.close(conn);
		}
	}
	
//	private boolean canModify(String modifyingUserId, Comment comment) {
//		return comment.getWriter().getId().equals(modifyingUserId);
//	}
}
