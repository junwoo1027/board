package kr.auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import kr.s2b.jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

public class LoginService {
	private MemberDAO memberDao = new MemberDAO();
	
	public User login(String id, String password) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(conn, id);
			if(member == null) {
				throw new LoginFailException();
			}
			if(!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName());
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
