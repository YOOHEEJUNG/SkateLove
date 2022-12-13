package sports.chat.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.chat.model.ChatDAO;
import sports.chat.model.ChatVO;
import sports.club.model.ClubVO;

public class ChatServiceImpl implements ChatService{

	//dao호출
	ChatDAO dao = ChatDAO.getInstance();
	
	@Override //클럽 글 작성
	public void write(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//dao메소드 사용
		dao.write(writer, title, content);
	}

	@Override //클럽 글 목록
	public ArrayList<ChatVO> getlist (HttpServletRequest request, HttpServletResponse response) {
		
		String search = request.getParameter("search");
		
		ArrayList<ChatVO> list = dao.list(search);
		
		return list;
	}

	
	@Override //클럽 글 상세내용확인
	public ChatVO getcontent(HttpServletRequest request, HttpServletResponse response) {
		
		String chno = request.getParameter("chno");
		ChatVO vo = dao.getContent(chno);
		
		return vo;
	}

	@Override //클럽 글 업데이트
	public void update(HttpServletRequest request, HttpServletResponse response) {
		
		String chno = request.getParameter("chno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		dao.update(chno, title, content);
		
		
	}

	@Override //글 삭제
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		String chno = request.getParameter("chno");
		
		//DAOs
		int result = dao.delete(chno);
		return result;
	}


	
	
	
}
