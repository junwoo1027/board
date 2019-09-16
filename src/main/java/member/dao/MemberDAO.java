package member.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import kr.s2b.jdbc.connection.JDBCUtil;
import member.model.Member;

public class MemberDAO {
	public Member selectById(Connection conn, String id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt=conn.prepareStatement("select * from mem where memberid = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member=null;
			if(rs.next()) {
				member= new Member(rs.getString("memberid"),
									rs.getString("name"),
									rs.getString("password"),
									toDate(rs.getTimestamp("regDate")));
			}
			return member;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date) {
		return date == null ? null: new Date(date.getTime());
	}
	
	public void insert(Connection conn, Member mem) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("insert into mem values(?, ?, ?, ?)")){
			pstmt.setString(1,  mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}
	
	public void update(Connection conn, Member mem) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("update mem set name=?, password=? where memberid=?")){
			pstmt.setString(1,  mem.getName());
			pstmt.setString(2, mem.getPassword());
			pstmt.setString(3, mem.getId());
			pstmt.executeUpdate();
		}
	}
	
	
	public int delete(Connection conn, Member mem) throws SQLException{
		try (PreparedStatement pstmt = conn.prepareStatement("delete from mem where memberid=?")){
			pstmt.setString(1, mem.getId());
			return pstmt.executeUpdate();
		}
	}
}
