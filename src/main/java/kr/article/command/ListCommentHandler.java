package kr.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.article.service.CommentPage;
import kr.article.service.ListCommentService;
import mvc.command.CommandHandler;

public class ListCommentHandler implements CommandHandler{

	private ListCommentService listService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String pageNoval = req.getParameter("pageNo");
		System.out.println(req.getParameter("pageNo"));
		int pageNo = 1;
		if(pageNoval != null) {
			pageNo = Integer.parseInt(pageNoval);
		}
		CommentPage commentPage = listService.getCommentPage(pageNo);
		req.setAttribute("commentPage", commentPage);
		return "/WEB-INF/view/readArticle.jsp";
	}
}
