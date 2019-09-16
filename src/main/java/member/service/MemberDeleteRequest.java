package member.service;

import java.util.Map;

public class MemberDeleteRequest {

	private String userId;
	private String password;
	
	public MemberDeleteRequest(String userId, String password) {
		this.userId=userId;
		this.password=password;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}
	
}
