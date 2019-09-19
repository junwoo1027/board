package kr.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;

import kr.article.model.Writer;
import kr.article.model.Article;
import kr.article.model.ArticleContent;
import kr.article.model.Comment;
import kr.s2b.jdbc.connection.JDBCUtil;;

public class CommentDao {

	public Comment insert(Connection conn, int no, Comment comment) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into comment"
					+"(article_no, writer_id, content, writer_name)"
					+"values (?, ?, ?, ?)");
//			pstmt.setInt(1, comment.getComNum());
			pstmt.setInt(1, no);
			pstmt.setString(2, comment.getWriter().getId());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, comment.getWriter().getName());
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
	
	public List<Comment> selectById(Connection conn,  int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from comment where article_no = ? ");
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			List<Comment> comment =new ArrayList<Comment>();
			while(rs.next()) {
				comment.add(convertComment(rs));
			}	
			
			return comment;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	}
	
	public Comment select(Connection conn,  int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from comment where comment_no = ? ");
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			Comment comment =null;
			if(rs.next()) {
				comment = convertComment(rs);
			}				
			return comment;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	}
	
	public Comment convertComment(ResultSet rs) throws SQLException{
		return new Comment(rs.getInt("comment_no"),
				rs.getInt("article_no"),
				new Writer(rs.getString("writer_id"),
						rs.getString("writer_name")),
				rs.getString("content"));
	}
	
//	private Timestamp toTimestamp(Date date) {
//		return new Timestamp(date.getTime());
//	}
	
	public int update(Connection conn, int no, String content) throws SQLException{
		try(PreparedStatement pstmt = 
				conn.prepareStatement("update comment set content = ?" + "where comment_no=?")){
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("delete from comment where comment_no=?")){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
	
	
}
