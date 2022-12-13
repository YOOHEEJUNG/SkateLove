package sports.location.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.location.model.LocationVO;

public interface LocationService {
	
	//인터페이스는 body가 없음
	public void regist(HttpServletRequest request, HttpServletResponse response);
	
	public ArrayList<LocationVO> loList(HttpServletRequest request, HttpServletResponse response);
	
	LocationVO loContent(HttpServletRequest request, HttpServletResponse response);
	
	public void update(HttpServletRequest request, HttpServletResponse response);
	
	int delete(HttpServletRequest request, HttpServletResponse response);
	
//	//검색
//	public ArrayList<LocationVO> searchList(HttpServletRequest request, HttpServletResponse response);
}
