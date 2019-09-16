package kr.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;

import kr.article.model.Writer;
import kr.article.model.Article;
import kr.article.model.Comment;
import kr.s2b.jdbc.connection.JDBCUtil;;

public class CommentDao {

	public Comment insert(Connection conn, int no, Comment comment) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into comment"
					+"(article_no, writer_id, content)"
					+"values (?, ?, ?)");
//			pstmt.setInt(1, comment.getComNum());
			pstmt.setInt(1, no);
			pstmt.setString(2, comment.getWriter().getId());
			pstmt.setString(3, comment.getContent());
			int insertedCount = pstmt.executeUpdate();
			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from comment");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Comment(newNum,
							null,
							comment.getWriter(),
							comment.getContent());
				}
			}
			return null;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
			JDBCUtil.close(pstmt);
		}
	}
	
//	private Timestamp toTimestamp(Date date) {
//		return new Timestamp(date.getTime());
//	}
}
