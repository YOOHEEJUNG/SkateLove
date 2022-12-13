package sports.chat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import sports.util.JDBCUtil;



public class ChatDAO {

	//나자신의 객체를 생성해서 1개로 제한합니다.
	private static ChatDAO instance = new ChatDAO();

	//직접 객체를 생성 할 수 없도록 생성자에 private
	private ChatDAO() {
		//드라이버 클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}
	//외부에서 객체생성을 요구할 때 getter메서드를 통해 1번의 객체를 반환
	public static ChatDAO getInstance() {
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

		String sql = "insert into chat VALUES (chat_seq.nextval, ?, ?, ?, sysdate)";

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
	public ArrayList<ChatVO> list(String search){
		ArrayList<ChatVO> list = new ArrayList<>();

		String sql = "select * from chat";

		try {
			
			if(search != null && !search.isEmpty()) {
				sql += " WHERE title LIKE '%' || ? || '%' order by chno desc";
			} else {
				sql += " order by chno desc";
			}
			
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			
			 if(search != null && !search.isEmpty()) {
	               pstmt.setString(1, search);
	            }

			rs = pstmt.executeQuery();

			//결과출력
			while(rs.next()) {

				ChatVO vo = new ChatVO();
				vo.setChno( rs.getInt("chno")); //vo.set으로 DB에서 가져온 데이터를 in
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
	public ChatVO getContent(String chno) {

		String sql = "select * from chat where chno = ?";
		ChatVO vo = new ChatVO();

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chno);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				vo.setChno(rs.getInt("chno"));
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
	public void update(String chno, String title, String content) {

		String sql = "update chat set title = ?, content=? where chno = ?";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, chno);

			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}


	}

	public int delete(String chno) { //글 삭제
		int result = 0;

		String sql = "delete from chat where chno=?";

		try {

			conn = DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chno);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return result;
	}


	public ArrayList<ChatVO> search_list(String search){

		String sql = "select * from chat";

		ArrayList<ChatVO> list = new ArrayList<>(); //배열 생성

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
				ChatVO vo = new ChatVO();
				vo.setChno(rs.getInt("chno"));	
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
