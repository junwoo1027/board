package kr.article.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.article.service.ArticleNotFoundException;
import kr.article.service.DeleteCommentService;
import kr.article.service.DeleteRequest;
import kr.article.service.PermissionDeniedException;
import kr.auth.service.User;
import mvc.command.CommandHandler;

public class DeleteCommentHandler implements CommandHandler{

	private static final String FORM_VIEW =  "/WEB-INF/view/delComForm.jsp";
	
	private DeleteCommentService deleteService = new DeleteCommentService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
//			User authUser = (User) req.getSession().getAttribute("authUser");

			DeleteRequest delReq = new DeleteRequest(no);

			req.setAttribute("delReq", delReq);
			return FORM_VIEW;
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		User authUser = (User) req.getSession().getAttribute("authUser");
		
		String noVal = req.getParameter("no").trim();
		int no = Integer.parseInt(noVal);

		DeleteRequest delReq = new DeleteRequest(authUser.getId(), no);
		req.setAttribute("delReq", delReq);
		
		try {
			deleteService.delete(delReq);
			return "/WEB-INF/view/delComSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
