package kr.article.service;

import java.util.Map;

import kr.article.model.Writer;

public class CommentRequest {

	private Writer writer;
	private String content;
	private int articleNumber;
	
	public CommentRequest(Writer writer, String content, int articleNumber){
		this.writer = writer;
		this.content = content;
		this.articleNumber=articleNumber;
	}
	
	public CommentRequest(int articleNumber) {
		this.articleNumber=articleNumber;
	}
	
	public Writer getWriter() {
		return writer;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getArticleNumber() {
		return articleNumber;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(content == null || content.trim().isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
	}
}
