package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.auth.service.LoginService;
import kr.auth.service.LoginFailException;
import kr.auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String id= trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));//form에서 전송한 id 파라미터와 pw파라미터 값을 구한다.
		
		Map<String ,Boolean> errors =new HashMap<>();
		req.setAttribute("errors", errors);//에러정보를 담을 맵 객체를 생성하고 errors 속성에 저장한다.
		
		if(id==null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if(password==null || password.isEmpty())
			errors.put("password", Boolean.TRUE); //id나 pw 값이 없을 경우 에러른 추가한다.
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;//에러가 존재하면 폼 뷰를 리턴한다.
		}
		
		try {
			User user = loginService.login(id,password);//loginService.login()을 이용해서 인증수행. 로그인 성공하면 User 객체를 리턴
			req.getSession().setAttribute("authUser", user);//User 객체를 세션의 authUser 속성에 저장
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			return null;
		}catch(LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}	
	}
	
	private String trim(String str) {
		return str == null?null:str.trim();
	}
}
