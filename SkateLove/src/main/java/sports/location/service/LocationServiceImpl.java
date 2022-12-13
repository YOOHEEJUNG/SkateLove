package sports.location.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.location.model.LocationDAO;
import sports.location.model.LocationVO;

public class LocationServiceImpl implements LocationService {

	@Override
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		LocationDAO dao = LocationDAO.getInstance(); //DAO객체 생성
		dao.regist(writer, title, content);
	}

	@Override
	public ArrayList<LocationVO> loList(HttpServletRequest request, HttpServletResponse response) {
		
		String searchWord = request.getParameter("searchWord");
		
		//DAO 객체생성
		LocationDAO dao = LocationDAO.getInstance();
		
		//DAO에서 메서드 호출
		ArrayList<LocationVO> list = dao.loList(searchWord);
		
		return list;
	}
	
//	@Override
//	public ArrayList<LocationVO> searchList(HttpServletRequest request, HttpServletResponse response) {
//		
//		String searchWord = request.getParameter("searchWord");
//		
//		LocationDAO dao = LocationDAO.getInstance();
//		
//		
//		ArrayList<LocationVO> list = dao.loList();
//		
//		return list;
//	}

	@Override
	public LocationVO loContent(HttpServletRequest request, HttpServletResponse response) {
		
		//a태그로 넘어오는 param
		String lno = request.getParameter("lno");
		
		//DAO가서 메서드 완성해야 함
		LocationDAO dao = LocationDAO.getInstance();
		LocationVO vo = dao.loContent(lno);
		
		return vo;
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) {

		//화면에서 넘어오는 값
		String lno = request.getParameter("lno");
//		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		LocationDAO dao = LocationDAO.getInstance();
		dao.update(lno, title, content);
		
		
	}

	@Override
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		//lno 파라미터 값 받아오기
		String lno = request.getParameter("lno");
		
		LocationDAO dao = LocationDAO.getInstance();
		int result = dao.delete(lno);
		
		return result;
	}

	
	
	

}
