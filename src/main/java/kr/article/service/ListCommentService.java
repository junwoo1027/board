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
		
		public CommentPage getCommentPage(int no) {
			try (Connection conn = ConnectionProvider.getConnection()){
//				int total = commentDao.selectCount(conn);
				List<Comment> content = commentDao.selectById(conn, no);
				if(content == null) {
					throw new RuntimeException();
				}
//				return new CommentPage(total, pageNum, size, content);
				return new CommentPage(content, no);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
