package sports.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.club.model.ClubVO;
import sports.club.service.ClubServiceImpl;


@WebServlet("*.club")
public class ClubController extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("utf-8");

		//요청분기
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());

		System.out.println("요청경로" + command);

		//서비스 기능 사용
		ClubServiceImpl service = new ClubServiceImpl();
		if(command.equals("/club/club_write.club")) {
	         request.getRequestDispatcher("club_write.jsp").forward(request, response);
	         
	         
	         } else if(command.equals("/club/registForm.club")) {//클럽 글 작성 	
			service.write(request, response);
			response.sendRedirect("club_list.club");


		} else if(command.equals("/club/club_list.club")) { //클럽 글 목록
			
			ArrayList<ClubVO> list = service.getlist(request, response);

			request.setAttribute("list", list);

			request.getRequestDispatcher("club_list.jsp").forward(request, response);
			

		}   else if(command.equals("/club/club_content.club")) { //클럽 글 내용확인

			ClubVO vo = service.getcontent(request, response);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("club_content.jsp").forward(request, response);

		} else if(command.equals("/club/club_modify.club")) { //클럽 글 수정페이지 화면

			ClubVO vo = service.getcontent(request, response);
			request.setAttribute("vo", vo);

			request.getRequestDispatcher("club_modify.jsp").forward(request, response);

		} else if(command.equals("/club/club_update.club")) { //클럽 글 수정
			service.update(request, response);

			response.sendRedirect("club_content.club?cno=" + request.getParameter("cno")); 


		}else if(command.equals("/club/club_delete.club")) { //클럽 글 삭제


			//성공 실패 결과 반환
			int result = service.delete(request, response);

			String msg = "";
			if(result == 1) { //삭제 성공
				msg = "삭제성공";
			} else { //삭제 실패
				msg = "삭제실패";
			}

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg +"');");
			out.println("location.href='club_list.club';");
			out.println("</script>");

		} 
//		
//		else if(command.equals("/club/club_search.club")) { //검색
//			
//			ArrayList<ClubVO> list = service.search_list(request, response);
//			
//			request.setAttribute("list", list);
//			
//			request.getRequestDispatcher("search_clubList.jsp").forward(request, response);
//			
//		}
//		
		
		
	
		
		
		
		
	}

}