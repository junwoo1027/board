package kr.article.service;

public class DeleteRequest {

	private String userId;
	private int articleNum;
	
	public DeleteRequest(String userId, int articleNum) {
		this.userId = userId;
		this.articleNum = articleNum;
	}
	
	
	public DeleteRequest(int articleNum) {
		this.articleNum = articleNum;
	}

	public String getUserId() {
		return userId;
	}

	public int getArticleNum() {
		return articleNum;
	}
}

