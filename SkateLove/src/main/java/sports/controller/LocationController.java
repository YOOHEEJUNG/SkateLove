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

import org.apache.catalina.connector.Response;

import sports.location.model.LocationVO;
import sports.location.service.LocationService;
import sports.location.service.LocationServiceImpl;


@WebServlet("*.location")
public class LocationController extends HttpServlet {
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
		LocationService service = new LocationServiceImpl(); //로케이션 서비스 객체 생성
		
		// 세션
		HttpSession session = null;
		
		if(command.equals("/location/location_write.location")) {
			request.getRequestDispatcher("location_write.jsp").forward(request, response);
		
			
		//글 작성 화면	
		} else if(command.equals("/location/registForm.location")) { 
			service.regist(request, response);
			
			response.sendRedirect("location_list.location");
			System.out.println(1);
		
			
		//글 목록화면	
		} else if (command.equals("/location/location_list.location")) { 
			ArrayList<LocationVO> list = service.loList(request, response);
			
			request.setAttribute("list", list); //한번만 나갈꺼니까 request
			request.getRequestDispatcher("location_list.jsp").forward(request, response);
		
			
		//상세 내용화면	
		} else if (command.equals("/location/location_content.location")) { 
			LocationVO vo = service.loContent(request, response);
//			System.out.println(vo.getLno());
			
			request.setAttribute("content", vo);
			System.out.println(1);
			request.getRequestDispatcher("location_content.jsp").forward(request, response);
			System.out.println(2);
			System.out.println(vo);
		}
		
		
		//수정화면에서 vo값 가져와야하니까
		else if (command.equals("/location/location_modify.location")) { 
			//조회한 글에 대한 정보를 조회 재활용 (서비스 영역에서 똑같이 사용되니까)
			LocationVO vo = service.loContent(request, response);
			request.setAttribute("content", vo);
			request.getRequestDispatcher("location_modify.jsp").forward(request, response);
	
		//업데이트해서 데이터베이스에 보내기	
		} else if (command.equals("/location/update.location")) { 
			service.update(request, response);
			response.sendRedirect("location_content.location?lno=" + request.getParameter("lno"));
			
			
		} else if (command.equals("/location/location_delete.location")) {
			
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
			out.println("location.href = 'location_list.location'; ");
			out.println("</script>");
		
		}
		
	}	
}
