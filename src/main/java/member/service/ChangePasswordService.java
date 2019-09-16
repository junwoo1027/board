package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import kr.s2b.jdbc.connection.JDBCUtil;
import kr.s2b.jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import kr.service.InvalidPasswordException;
import kr.service.MemberNotFoundException;
import member.model.Member;

public class ChangePasswordService {

	private MemberDAO memberDao = new MemberDAO();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, userId);
			if (member == null) {
				throw new MemberNotFoundException();
			}
			if (!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			member.changePassword(newPwd);
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(conn);
		}
	}
}

