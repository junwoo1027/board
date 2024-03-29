package kr.article.service;

import java.util.Map;

import kr.article.model.Writer;

public class WriteRequest {

	private Writer writer;
	private String title;
	private String content;
	private String fileName;
	
	

	public WriteRequest(Writer writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
