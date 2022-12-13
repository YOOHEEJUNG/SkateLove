package sports.re.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import sports.location.model.LocationVO;
import sports.util.JDBCUtil;

public class ReDAO {
	
	// 나 자신 객체를 생성해서 한개로 제한
	private static ReDAO instance = new ReDAO();
	
	// 직접 객체 생성x - 생성자에 private
	private ReDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}
	
	// 외부에서 객체 생성을 요구할 때 getter 메서드를 통해 1번의 객체를 반환
	// 메서드로 이 객체를 계속 불러내서 사용
	public static ReDAO getInstance() {
		return instance;
	}
	
	// 필요한 데이터베이스 변수 선언
	public String url = "jdbc:oracle:thin:@172.30.1.49:1521:xe";
	public String uid = "sports";
	public String upw = "sports";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// 메서드

	public void regist(String writer, String title, String content) {
		System.out.println(1);
		String sql = "insert into re values (re_seq.nextval, ?, ?, ?, sysdate)";
		
		try {
			
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
	}
	
	
	public ArrayList<ReVO> reList(String search) {

		ArrayList<ReVO> list = new ArrayList<>();

		String sql = "SELECT * FROM re";

		try {
			// 검색어가 있는 경우
			if(search != null && !search.isEmpty()) {
				sql += " WHERE title LIKE '%' || ? || '%' order by rno desc";
			} else {
				sql += " order by rno desc";
			}

			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);

			if(search != null && !search.isEmpty()) {
				pstmt.setString(1, search);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {

				//한 행씩 받기
				int rno = rs.getInt("rno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");

				//LocationVO 객체 생성 후 생성자로 한번에 담기
				ReVO vo = new ReVO(rno, writer, title, content, regdate);

				//list에 객체 저장
				list.add(vo);

			}
			
//			//검색어가 없는 경우
//			if(search == null) {
//				rs = pstmt.executeQuery();
//				while (rs.next()) {
//
//					//한 행씩 받기
//					int rno = rs.getInt("rno");
//					String writer = rs.getString("writer");
//					String title = rs.getString("title");
//					String content = rs.getString("content");
//					Timestamp regdate = rs.getTimestamp("regdate");
//
//					//LocationVO 객체 생성 후 생성자로 한번에 담기
//					ReVO vo = new ReVO(rno, writer, title, content, regdate);
//
//					//list에 객체 저장
//					list.add(vo);
//
//				}
//			}
		

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		//list 반환
		return list;
	}
	
	//글 내용 확인
	public ReVO reContent(String rno) {
		
		ReVO vo = new ReVO();
		
		String sql = "select * from re where rno = ?";
		
		
		try {
			
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setRno(rs.getInt("rno"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	//수정메서드
	public void update(String rno, String title, String content) {
		
		String sql = "update re set title = ?, content = ? where rno = ?";
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, rno);
			
			pstmt.executeUpdate(); //void형이니까 여기서 끝
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
	}
	
	//삭제메서드
	public int delete(String rno) {
		int result = 0;
		String sql = "delete from re where rno = ?";
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rno);
			
			result = pstmt.executeUpdate();
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	
		return result;
	}
	
	
	//검색메서드
	public ArrayList<ReVO> reSearch(String search) {
		
			ArrayList<ReVO> list = new ArrayList<>();
			
			String sql = "SELECT * FROM re";
			
			try {
			
				if(search != null && !search.isEmpty()) {
					sql += " WHERE title LIKE '%' || ? || '%'";
				}
				
				conn = DriverManager.getConnection(url, uid, upw);
				pstmt = conn.prepareStatement(sql);
				
				if(search != null && !search.isEmpty()) {
					pstmt.setString(1, search);
				}
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int rno = rs.getInt("rno");
					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp regdate = rs.getTimestamp("regdate");
					
					//ReVO 객체 생성 후 생성자로 한번에 담기
					ReVO vo2 = new ReVO(rno, writer, title, content, regdate);
					
					//list에 객체 저장
					list.add(vo2);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			
			return list;

	}
	
	
}
