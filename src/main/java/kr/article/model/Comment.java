package kr.article.model;

public class Comment {

	private Integer comNum;
	private Integer number;
	private Writer writer;
	private String content;
	
	public Comment(Integer comNum, Integer number, Writer writer, String content) {
		this.comNum=comNum;
		this.number=number;
		this.writer=writer;
//		this.regDate=regDate;
		this.content=content;
	}

	public Integer getNumber() {
		return number;
	}
	
	public Integer getComNum() {
		return comNum;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getContent() {
		return content;
	}
}
