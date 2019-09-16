package member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberDeleteService;
import member.service.MemberDeleteRequest;
import kr.auth.service.User;
import kr.service.InvalidPasswordException;
import kr.service.MemberNotFoundException;

import mvc.command.CommandHandler;

public class MemberDeleteHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/memberDeleteForm.jsp";
	private MemberDeleteService memDelService = new MemberDeleteService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		User authUser = (User) req.getSession().getAttribute("authUser");
		String password = req.getParameter("password").trim();

		System.out.println(authUser);
		System.out.println(req.getParameter("password"));
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		if(password == null || password.isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}

		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			memDelService.delete(authUser.getId(), password);
			return "/WEB-INF/view/memDeleteSuccess.jsp";
		}catch(InvalidPasswordException e) {
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
