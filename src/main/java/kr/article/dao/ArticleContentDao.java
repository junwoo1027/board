package kr.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import kr.article.model.ArticleContent;
import kr.s2b.jdbc.connection.JDBCUtil;

public class ArticleContentDao {

	public ArticleContentDao() {}
	
	public ArticleContent insert(Connection conn, ArticleContent content) 
			throws SQLException {
				PreparedStatement pstmt = null;
				try {
					pstmt = conn.prepareStatement(
							"insert into content " + 
							"(article_no, content) values (?,?)");
					pstmt.setLong(1, content.getNumber());
					pstmt.setString(2, content.getContent());
					int insertedCount = pstmt.executeUpdate();
					if (insertedCount > 0) {
						return content;
					} else {
						return null;
					}
				} finally {
					JDBCUtil.close(pstmt);
				}
			}
		
		public ArticleContent selectById(Connection conn,  int no) throws SQLException{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("select * from content where article_no = ? ");
				pstmt.setInt(1, no);
				rs=pstmt.executeQuery();
				ArticleContent content =null;
				if(rs.next()) {
					content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
				}
				return content;
			}finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(pstmt);
			}
		}
		
		public int update(Connection conn, int no, String content) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement("update content set content = ? " + "where article_no=?")){
				pstmt.setString(1,  content);
				pstmt.setInt(2,  no);
				return pstmt.executeUpdate();
			}
		}
		
		public int delete(Connection conn, int no) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement("delete from content where article_no=?")){
				pstmt.setInt(1,  no);
				return pstmt.executeUpdate();
			}
		}
}
