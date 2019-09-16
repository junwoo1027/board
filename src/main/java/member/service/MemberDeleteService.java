package member.service;

import java.sql.Connection;
import java.sql.SQLException;


import member.dao.MemberDAO;
import member.model.Member;

import kr.service.MemberNotFoundException;
import kr.service.InvalidPasswordException;
import kr.s2b.jdbc.connection.ConnectionProvider;
import kr.s2b.jdbc.connection.JDBCUtil;

public class MemberDeleteService {

	private MemberDAO memberDao = new MemberDAO();

	public void delete(String userId, String password) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member mem = memberDao.selectById(conn, userId);
			if (mem == null) {
				throw new MemberNotFoundException();
			}
			if (!mem.matchPassword(password)) {
				throw new InvalidPasswordException();
			}			
			memberDao.delete(conn, mem);
			memberDao.delete(conn, mem);
			conn.commit();
		} catch (SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JDBCUtil.rollback(conn);
			throw e;
		} finally {
			JDBCUtil.close(conn);
		}
	}
}
