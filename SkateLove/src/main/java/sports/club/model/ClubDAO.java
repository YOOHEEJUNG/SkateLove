package sports.club.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import sports.util.JDBCUtil;



public class ClubDAO {

	//나자신의 객체를 생성해서 1개로 제한합니다.
	private static ClubDAO instance = new ClubDAO();

	//직접 객체를 생성 할 수 없도록 생성자에 private
	private ClubDAO() {
		//드라이버 클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}
	//외부에서 객체생성을 요구할 때 getter메서드를 통해 1번의 객체를 반환
	public static ClubDAO getInstance() {
		return instance;
	}

	//필요한 데이터베이스 변수 선언
	public String URL = "jdbc:oracle:thin:@172.30.1.49:1521:xe";
	public String UID = "sports";
	public String UPW = "sports";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//5. 메서드

	//글 작성 메서드
	public void write(String writer, String title, String content) {

		String sql = "insert into club VALUES (club_seq.nextval, ?, ?, ?, sysdate)";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	}

	//글 목록 메서드
	public ArrayList<ClubVO> list(String search){
		ArrayList<ClubVO> list = new ArrayList<>();

		String sql = "select * from club";

		try {
			
			if(search != null && !search.isEmpty()) {
				sql += " WHERE title LIKE '%' || ? || '%' order by cno desc";
			} else {
				sql += " order by cno desc";
			}
			
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			
			 if(search != null && !search.isEmpty()) {
	               pstmt.setString(1, search);
	            }

			rs = pstmt.executeQuery();
			
			
			
			
//			
//			conn = DriverManager.getConnection(URL, UID, UPW);
//			pstmt = conn.prepareStatement(sql);
//
//			rs =  pstmt.executeQuery(); //?

			//결과출력
			while(rs.next()) {

				ClubVO vo = new ClubVO();
				vo.setCno( rs.getInt("cno")); //vo.set으로 DB에서 가져온 데이터를 in
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setRegdate(rs.getTimestamp("regdate"));

				//list에 vo 추가
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}


	//글 상세내용확인	
	public ClubVO getContent(String cno) {

		String sql = "select * from club where cno = ?";
		ClubVO vo = new ClubVO();

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cno);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				vo.setCno(rs.getInt("cno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
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

	//글 수정
	public void update(String cno, String title, String content) {

		String sql = "update club set title = ?, content=? where cno = ?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, cno);

			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}


	}

	public int delete(String cno) { //글 삭제
		int result = 0;

		String sql = "delete from club where cno=?";

		try {

			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cno);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return result;
	}


	public ArrayList<ClubVO> search_list(String search){

		String sql = "select * from club";

		ArrayList<ClubVO> list = new ArrayList<>(); //배열 생성

		try {
			if(search != null && !search.isEmpty()) {
				sql += " WHERE title LIKE '%' || ? || '%'";
			} //검색어 null인지 아닌지 확인
			
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			
			 if(search != null && !search.isEmpty()) {
	               pstmt.setString(1, search);
	            }

			rs = pstmt.executeQuery();


			while(rs.next()){
				ClubVO vo = new ClubVO();
				vo.setCno(rs.getInt("cno"));	
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));

				list.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return list;
	}



}
