package sports.club.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.club.model.ClubDAO;
import sports.club.model.ClubVO;

public class ClubServiceImpl implements ClubService{

	//dao호출
	ClubDAO dao = ClubDAO.getInstance();
	
	@Override //클럽 글 작성
	public void write(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//dao메소드 사용
		dao.write(writer, title, content);
	}

	@Override //클럽 글 목록
	public ArrayList<ClubVO> getlist (HttpServletRequest request, HttpServletResponse response) {
		
		String search = request.getParameter("search");
		
		ArrayList<ClubVO> list = dao.list(search);
		
		return list;
	}

	
	@Override //클럽 글 상세내용확인
	public ClubVO getcontent(HttpServletRequest request, HttpServletResponse response) {
		
		String cno = request.getParameter("cno");
		ClubVO vo = dao.getContent(cno);
		
		return vo;
	}

	@Override //클럽 글 업데이트
	public void update(HttpServletRequest request, HttpServletResponse response) {
		
		String cno = request.getParameter("cno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		dao.update(cno, title, content);
		
		
	}

	@Override //글 삭제
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		String cno = request.getParameter("cno");
		
		//DAOs
		int result = dao.delete(cno);
		return result;
	}

//	@Override //글 검색
//	public ArrayList<ClubVO> search_list(HttpServletRequest request, HttpServletResponse response) {
//		
//		
//		ArrayList<ClubVO> list = dao.search_list(search);
//		
//		return list;
//	}
	
	
	
}
