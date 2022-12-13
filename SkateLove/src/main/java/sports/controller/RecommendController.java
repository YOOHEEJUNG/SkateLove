package sports.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sports.location.model.LocationVO;
import sports.re.model.ReVO;
import sports.re.service.ReService;
import sports.re.service.ReServiceImpl;


@WebServlet("*.re")
public class RecommendController extends HttpServlet {
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
		
		//서비스 객체
		ReService service = new ReServiceImpl(); //로케이션 서비스 객체 생성
		
		// 세션
		HttpSession session = null;
		
		if(command.equals("/re/re_write.re")) {
			request.getRequestDispatcher("re_write.jsp").forward(request, response);
		
			
		} else if(command.equals("/re/registForm.re")) { //글 작성 화면
			service.regist(request, response);
			
			response.sendRedirect("re_list.re");
			System.out.println(1);
			
			
		} else if (command.equals("/re/re_list.re")) { //글 목록화면
			ArrayList<ReVO> list = service.reList(request, response);
			request.setAttribute("list", list); //한번만 나갈꺼니까 request
			
			request.getRequestDispatcher("re_list.jsp").forward(request, response);
		
			
		} else if (command.equals("/re/re_content.re")) { //상세 내용화면
			ReVO vo = service.reContent(request, response);
//			System.out.println(vo.getRno());
			
			request.setAttribute("content", vo);
			System.out.println(1);
			request.getRequestDispatcher("re_content.jsp").forward(request, response);
			System.out.println(2);
			System.out.println(vo);
			
			
		} else if (command.equals("/re/re_modify.re")) { //수정화면에서 vo값 가져와야하니까
			//조회한 글에 대한 정보를 조회 재활용 (서비스 영역에서 똑같이 사용되니까)
			ReVO vo = service.reContent(request, response);
			
			request.setAttribute("content", vo);
			
			request.getRequestDispatcher("re_modify.jsp").forward(request, response);
	
			
		} else if (command.equals("/re/update.re")) { //업데이트해서 데이터베이스에 보내기
			
			service.update(request, response);
			
			response.sendRedirect("re_content.re?rno=" + request.getParameter("rno"));
			
			
		} else if (command.equals("/re/re_delete.re")) {
			
			String msg = "";
			int result = service.delete(request, response);
			
			if(result == 1) { 
				msg = "삭제성공"; //삭제 성공 팝업
			} else { //삭제실패
				msg= "삭제실패";
				
			}
			
			response.setContentType("text/html; charset = utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+ msg + "');");
			out.println("location.href = 're_list.re'; ");
			out.println("</script>");

		}
	
	
	}
}
