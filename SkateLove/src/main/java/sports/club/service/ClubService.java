package sports.club.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.club.model.ClubVO;

public interface ClubService {

	public void write(HttpServletRequest request, HttpServletResponse response); //글 작성
	public ArrayList<ClubVO> getlist(HttpServletRequest request, HttpServletResponse response); //글 목록
	public ClubVO getcontent(HttpServletRequest request, HttpServletResponse response);//글 확인
	public void update(HttpServletRequest request, HttpServletResponse response);//글 수정
	int delete(HttpServletRequest request, HttpServletResponse response); //글 삭제
//	public ArrayList<ClubVO> search_list(HttpServletRequest request, HttpServletResponse response); //글 검색
	
}
