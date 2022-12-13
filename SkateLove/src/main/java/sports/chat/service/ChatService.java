package sports.chat.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.chat.model.ChatVO;
import sports.club.model.ClubVO;

public interface ChatService {

	public void write(HttpServletRequest request, HttpServletResponse response); //글 작성
	public ArrayList<ChatVO> getlist(HttpServletRequest request, HttpServletResponse response); //글 목록
	public ChatVO getcontent(HttpServletRequest request, HttpServletResponse response);//글 확인
	public void update(HttpServletRequest request, HttpServletResponse response);//글 수정
	int delete(HttpServletRequest request, HttpServletResponse response); //글 삭제
	
	
}
