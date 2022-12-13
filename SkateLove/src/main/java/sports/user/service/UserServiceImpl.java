package sports.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sports.user.model.UserDAO;
import sports.user.model.UserVO;

public class UserServiceImpl implements UserService {

	UserDAO dao = UserDAO.getInstance();
	@Override
	//회원가입 서비스 제공
	public int join(HttpServletRequest request, HttpServletResponse response) {
		
		UserDAO dao = UserDAO.getInstance();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String phonenumber = request.getParameter("phonenumber");
		String gender = request.getParameter("gender");
		
		int result = dao.idCheck(id);
		
		if(result>=1) { //가입 실패
			return 1;
		}else { //result값이 0이면 같은 id의 회원이 0이기 때문에
			UserVO vo = new UserVO( id, pw, name, age, phonenumber, gender);
			dao.join(vo);
			return 0;
		}
		
	}

	
	@Override
	//로그인 서비스 제공
	public UserVO login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String auto = request.getParameter("autoLogin");
		
		UserDAO dao= UserDAO.getInstance(); 
		UserVO vo = dao.login(id, pw);
		return vo;
	}
	
	
	
	//회원 정보 조회 서비스
	@Override
	public UserVO getInfo(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("user_id");
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.getInfo(id);
		
		return vo;
	}
	
	
	
	//회원 정보 수정 서비스 
	@Override
	public int update(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String phonenumber = request.getParameter("phonenumber");
		String gender = request.getParameter("gender");
		
		//UserDAO 객체는 Private로 선언되었기에 getter메서드로만 호출가능
		UserDAO dao = UserDAO.getInstance(); 
		int result = dao.update(id, pw, name, age, phonenumber, gender);
		
		if(result == 1 ) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
		}
		
		return result;
	}

		
	//회원 탈퇴 서비스 제공
	@Override
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		String pw = request.getParameter("pw");


		int result = 0;
		UserVO vo = dao.login(id,pw);
		if(vo != null) {
			result = dao.delete(id);

		}
		if(result == 1) {//탈퇴 성공
			session.invalidate();
			
		}
		
		return result;
	}


}
