package kr.article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.article.model.Writer;
import kr.article.service.ArticleNotFoundException;
import kr.article.service.CommentRequest;
import kr.article.service.WriteCommentService;
import kr.auth.service.User;
import mvc.command.CommandHandler;

public class WriteCommentHandler implements CommandHandler{

	private static final String FORM_VIEW ="/WEB-INF/view/readArticle.jsp";
	private WriteCommentService writeCommentService = new WriteCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		try {
//			String noVal = req.getParameter("no");
//			int no = Integer.parseInt(noVal);
//			
//			CommentRequest comReq = new CommentRequest(no);
//			
//			req.setAttribute("comReq", comReq);
			return FORM_VIEW;
//		}catch(ArticleNotFoundException e) {
//			res.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
//		System.out.println(user);
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
//		CommentRequest comReq = createCommentRequest(user, req);
		CommentRequest comReq = new CommentRequest(new Writer(user.getId(), user.getName()),
				req.getParameter("content"), no);
		req.setAttribute("comReq", comReq);
		req.setAttribute("no", no);
		comReq.validate(errors);
//		System.out.println(req.getParameter("content"));
		
		if(!errors.isEmpty()) {
			return "/WEB-INF/view/test.jsp";
		}
		
//		int newComment = writeCommentService.write(comReq);
//		req.setAttribute("newComment", newComment);
//		
//		return "/WEB-INF/view/newCommentSuccess.jsp";
		try {
			writeCommentService.write(comReq);
			return "/WEB-INF/view/newCommentSuccess.jsp";
		}catch(ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
		
	
//	private CommentRequest createCommentRequest(User user, HttpServletRequest req) {
//		String noVal = req.getParameter("no");
//		int no = Integer.parseInt(noVal);
//		System.out.println(req.getParameter("no"));
//		System.out.println(req.getParameter("content"));
//		return new CommentRequest(
//				new Writer(user.getId(), user.getName()),
//				req.getParameter("content"),
//				no);
//	}
//}
