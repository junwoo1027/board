package kr.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.article.dao.CommentDao;
import kr.article.model.Comment;
import kr.s2b.jdbc.connection.ConnectionProvider;

public class ListCommentService {

		private CommentDao commentDao = new CommentDao();
		private int size=3;
		
		public CommentPage getCommentPage(int pageNum) {
			try (Connection conn = ConnectionProvider.getConnection()){
				int total = commentDao.selectCount(conn);
				List<Comment> content = commentDao.select(conn, (pageNum-1) * size, size);
				return new CommentPage(total, pageNum, size, content);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
