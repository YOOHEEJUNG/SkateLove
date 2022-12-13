package sports.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sports.user.model.UserVO;
import sports.user.service.UserServiceImpl;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//한글 처리
		request.setCharacterEncoding("utf-8");

		//요청분기
		String uri = request.getRequestURI(); // uri를 받아오기
		String path = request.getContextPath(); //컨텍스트패스 받아오기

		String command = uri.substring(path.length()); //uri를 패스의 길이만큼 잘라서 command에 저장 

		System.out.println("요청경로:" + command);

		//서비스 객체 생성
		UserServiceImpl service = new UserServiceImpl();
		//세션 객체
		HttpSession session = request.getSession();


		switch (command) {

		//회원가입페이지
		case "/user/user_join.user":

			request.getRequestDispatcher("user_join.jsp").forward(request, response);
			break;


		case "/user/joinForm.user":

			int result = service.join(request, response);

			//아이디 중복여부 확인 
			if(result>=1) { // 아이디 중복 -> 가입실패
				request.setAttribute("msg", "아이디가 중복입니다");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
			}else { //가입 성공
				request.setAttribute("join", "정상적으로 가입되었습니다");

				response.setContentType("text/html; charset = utf-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('정상적으로 가입되었습니다');");
				out.println("location.href='user_login.user';");
				out.println("</script>");

			}




			break;

		case "/user/user_login.user": //로그인 페이지

			request.getRequestDispatcher("user_login.jsp").forward(request, response);
			break;


			//로그인 시도할 때
		case "/user/loginForm.user":

			//vo 객체 불러오기
			UserVO vo = service.login(request, response);

			if(vo == null) { //로그인 실패
				request.setAttribute("fail", "아이디와 비밀번호를 확인하세요");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);

			}else { //로그인 성공
				//로그인한 아이디 세션에 저장


				//로그인 성공시 id쿠키 생성
				Cookie cookie = new Cookie("user_id", vo.getId());
				//유효시간 설정
				cookie.setMaxAge(1800);
				//저장
				response.addCookie(cookie);

				session.setAttribute("user_id", vo.getId());
				session.setAttribute("user_pw", vo.getPw());
				session.setAttribute("user_name", vo.getName());
				session.setAttribute("user_phoneno", vo.getPhoneno());
				session.setAttribute("user_age", vo.getAge());
				session.setAttribute("user_gender", vo.getGender());


				//idCheck쿠키 생성
				//사용자가 체크박스를 체크하면 idCheck를 생성하고 시간은 30
				String autoLogin = request.getParameter("autoLogin");
				if(autoLogin != null){
					Cookie check = new Cookie("re_id", vo.getId());
					check.setMaxAge(30);
					response.addCookie(check);

				}

				response.sendRedirect("user_mypage.user");//메인으로 이동(꼭 확인)

			}
			break;

			//마이페이지로 이동
		case "/user/user_mypage.user":


			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);

			break;

			//정보수정화면으로 이동
		case "/user/user_modify.user":

			UserVO VO = service.getInfo(request, response);
			request.setAttribute("vo", VO);

			request.getRequestDispatcher("user_modify.jsp").forward(request, response);
			break;

			//정보수정화면 출력
		case "/user/updateForm.user":

			int result1 = service.update(request, response);

			if(result1 == 1) { //수정 완료

				request.setAttribute("update", "수정되었습니다");

				response.setContentType("text/html; charset = utf-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('정보가 수정되었습니다');");
				out.println("location.href='user_mypage.user';");
				out.println("</script>");

			}else {//수정 실패
				response.sendRedirect("user_modify.user");
			}

			break;

			//로그아웃(확인)
		case "/user/user_logout.user":

			session.invalidate(); // 세션무효화
			response.sendRedirect("../include/header.jsp");//메인으로 이동 

			break;
			
			
		case "/user/user_delete.user":
			request.getRequestDispatcher("user_delete.jsp").forward(request, response);

			break;


		case "/user/deleteForm.user":
			
			int rs = service.delete(request, response);
			String userPw = request.getParameter("pw");
			
			if(rs == 1 || userPw.equals("pw")) { //탈퇴 성공
				request.setAttribute("delete", "정상적으로 탈퇴되었습니다");
				
				response.setContentType("text/html; charset = utf-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('정상적으로 탈퇴되었습니다');");
				out.println("location.href='user_join.user';");
				out.println("</script>");
				
				
			}else { // 탈퇴 실패
				response.sendRedirect("user_mypage.user"); //마이페이지로 이동
			}
			break;

		

		default:
			break;
		}
	}

}