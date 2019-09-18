package kr.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.article.service.ArticleContentNotFoundException;
import kr.article.service.ArticleData;
import kr.article.service.ArticleNotFoundException;
import kr.article.service.CommentPage;
import kr.article.service.ListCommentService;
import kr.article.service.ReadArticleService;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readService = new ReadArticleService();
	private ListCommentService commentService = new ListCommentService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		
		try {
			ArticleData articleData = readService.getArticle(articleNum, true);
			req.setAttribute("articleData", articleData);
			CommentPage commentlist = new CommentPage(articleNum);
			commentlist = commentService.getCommentPage(articleNum);
			req.setAttribute("comment", commentlist);
			return "/WEB-INF/view/readArticle.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}

