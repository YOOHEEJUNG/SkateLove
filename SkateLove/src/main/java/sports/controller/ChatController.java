package sports.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.chat.model.ChatVO;
import sports.chat.service.ChatServiceImpl;


@WebServlet("*.chat")
public class ChatController extends HttpServlet {

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
		ChatServiceImpl service = new ChatServiceImpl();
		if(command.equals("/chat/chat_write.chat")) {
	         request.getRequestDispatcher("chat_write.jsp").forward(request, response);
	         
	         
	         } else if(command.equals("/chat/registForm.chat")) {//클럽 글 작성 	
			service.write(request, response);
			response.sendRedirect("chat_list.chat");


		} else if(command.equals("/chat/chat_list.chat")) { //클럽 글 목록
			
			ArrayList<ChatVO> list = service.getlist(request, response);

			request.setAttribute("list", list);

			request.getRequestDispatcher("chat_list.jsp").forward(request, response);
			

		}   else if(command.equals("/chat/chat_content.chat")) { //클럽 글 내용확인

			ChatVO vo = service.getcontent(request, response);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("chat_content.jsp").forward(request, response);

		} else if(command.equals("/chat/chat_modify.chat")) { //클럽 글 수정페이지 화면

			ChatVO vo = service.getcontent(request, response);
			request.setAttribute("vo", vo);

			request.getRequestDispatcher("chat_modify.jsp").forward(request, response);

		} else if(command.equals("/chat/chat_update.chat")) { //클럽 글 수정
			service.update(request, response);

			response.sendRedirect("chat_content.chat?chno=" + request.getParameter("chno")); 


		}else if(command.equals("/chat/chat_delete.chat")) { //클럽 글 삭제


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
			out.println("location.href='chat_list.chat';");
			out.println("</script>");

		} 

		
		
	
		
		
		
		
	}

}