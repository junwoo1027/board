package kr.article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.article.model.Writer;
import kr.article.service.WriteArticleService;
import kr.article.service.WriteRequest;
import kr.auth.service.User;
import mvc.command.CommandHandler;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;;

public class WriteArticleHandler implements CommandHandler{
	private static final String FORM_VIEW = "/newArticleForm.jsp";
	private WriteArticleService writeService = new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		
//		MultipartRequest multi = null;
//		int sizeLimit = 10 * 1024 * 1024;
//		String savePath = req.getRealPath("/upload");
//		try {
//			multi=new MultipartRequest(req, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		String filename = multi.getFilesystemName("file");
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		
		return "/WEB-INF/view/newArticleSuccess.jsp";
	}
	
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
	}
	
}
