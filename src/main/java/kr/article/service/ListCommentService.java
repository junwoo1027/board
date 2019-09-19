package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.article.dao.CommentDao;
import kr.article.model.Comment;
import kr.s2b.jdbc.connection.ConnectionProvider;

public class ListCommentService {

		private CommentDao commentDao = new CommentDao();
//		private int size=3;
		
		public List<Comment> CommentPage(int no) {
			try (Connection conn = ConnectionProvider.getConnection()){
				
				return commentDao.selectById(conn, no);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
