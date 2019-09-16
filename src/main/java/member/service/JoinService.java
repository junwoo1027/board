package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import kr.s2b.jdbc.connection.ConnectionProvider;
import kr.s2b.jdbc.connection.JDBCUtil;
import member.dao.MemberDAO;
import member.model.Member;

public class JoinService {
	
	private MemberDAO memberDAO = new MemberDAO();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
		
			Member member = memberDAO.selectById(conn, joinReq.getId());
			if(member != null) {
				JDBCUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			
			memberDAO.insert(conn, new Member(
										joinReq.getId(),
										joinReq.getName(),
										joinReq.getPassword(),
										new Date())
					);
			conn.commit();
		}catch(SQLException e){
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JDBCUtil.close(conn);
		}
	}
}
