package sports.re.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sports.location.model.LocationVO;
import sports.re.model.ReVO;

public interface ReService {

	//인터페이스는 body가 없음
		public void regist(HttpServletRequest request, HttpServletResponse response);
		
		public ArrayList<ReVO> reList(HttpServletRequest request, HttpServletResponse response);
		
		ReVO reContent(HttpServletRequest request, HttpServletResponse response);
		
		public void update(HttpServletRequest request, HttpServletResponse response);
		
		int delete(HttpServletRequest request, HttpServletResponse response);
		
}