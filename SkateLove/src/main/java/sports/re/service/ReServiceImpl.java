package sports.re.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.location.model.LocationDAO;
import sports.location.model.LocationVO;
import sports.re.model.ReDAO;
import sports.re.model.ReVO;

public class ReServiceImpl implements ReService {

	@Override
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		ReDAO dao = ReDAO.getInstance(); //DAO객체 생성
		dao.regist(writer, title, content);
		
	}

	@Override
	public ArrayList<ReVO> reList(HttpServletRequest request, HttpServletResponse response) {
		
		String searchWord = request.getParameter("searchWord");
		
		//DAO 객체생성
		ReDAO dao = ReDAO.getInstance();
		
		//DAO에서 메서드 호출
		ArrayList<ReVO> list = dao.reList(searchWord);
		
		return list;
	}

	@Override
	public ReVO reContent(HttpServletRequest request, HttpServletResponse response) {
		
		//a태그로 넘어오는 param
		String rno = request.getParameter("rno");
		
		//DAO가서 메서드 완성해야 함
		ReDAO dao = ReDAO.getInstance();
		ReVO vo = dao.reContent(rno);
		
		return vo;
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) {

		//화면에서 넘어오는 값
		String rno = request.getParameter("rno");
//		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		ReDAO dao = ReDAO.getInstance();
		dao.update(rno, title, content);
	}

	@Override
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		//rno 파라미터 값 받아오기
		String rno = request.getParameter("rno");
		
		ReDAO dao = ReDAO.getInstance();
		int result = dao.delete(rno);
		
		return result;
	}
	
	
}
