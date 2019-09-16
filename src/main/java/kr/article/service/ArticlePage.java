package kr.article.service;

import java.util.List;

import kr.article.model.Article;

public class ArticlePage {
	private int total;//전체 개시글의 개수
	private int currentPage;//사용자가 요청한 페이지 번호
	private List<Article> content;//화면한 출력하 게시글 목록
	private int totalPages;//전체 페이지 개수
	private int startPage;
	private int endPage;
	
	public ArticlePage(int total, int currentPage, int size, List<Article> content) {
		this.total=total;
		this.currentPage=currentPage;
		this.content=content;
		if(total == 0) {
			totalPages=0;
			startPage=0;
			endPage=0;
		}else {
			totalPages=total/size;
			if(total % size > 0) {
				totalPages++;
			}//ex) 전체개시글 34 size 10  나누기몫=3 나머지는 4  totalpage=3+1
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal==0)startPage -=5;
			
			endPage = startPage +4;
			if(endPage > totalPages)endPage = totalPages;
		}
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	public boolean hasNoArticles() {
		return total ==0;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}
}
