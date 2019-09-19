package kr.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.article.service.ArticlePage;
import kr.article.service.ListArticleService;
import mvc.command.CommandHandler;

public class ListArticleHandler implements CommandHandler {

	private ListArticleService listService = new ListArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
//		String col = req.getParameter("col");
//		String word = req.getParameter("word");

		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ArticlePage articlePage = listService.getArticlePage(pageNo);
		req.setAttribute("articlePage", articlePage);
		return "/WEB-INF/view/listArticle.jsp";
	}

}
