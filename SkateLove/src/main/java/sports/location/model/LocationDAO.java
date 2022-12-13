package sports.location.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import sports.util.JDBCUtil;

public class LocationDAO {

	// 나 자신 객체를 생성해서 한개로 제한
	private static LocationDAO instance = new LocationDAO();

	// 직접 객체 생성x - 생성자에 private
	private LocationDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버클래스 로드에러");
		}
	}

	// 외부에서 객체 생성을 요구할 때 getter 메서드를 통해 1번의 객체를 반환
	// 메서드로 이 객체를 계속 불러내서 사용
	public static LocationDAO getInstance() {
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
		String sql = "insert into location values (location_seq.nextval, ?, ?, ?, sysdate)";

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


	@SuppressWarnings("unused")
	public ArrayList<LocationVO> loList(String search) {

		ArrayList<LocationVO> list = new ArrayList<>();

		String sql = "SELECT * FROM location";

		try {
			// 검색어가 있는 경우
			if(search != null && !search.isEmpty()) {
				sql += " WHERE title LIKE '%' || ? || '%' order by lno desc";
			} else {
				sql += " order by lno desc";
			}

			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);

			if(search != null && !search.isEmpty()) {
				pstmt.setString(1, search);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {

				//한 행씩 받기
				int lno = rs.getInt("lno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");

				//LocationVO 객체 생성 후 생성자로 한번에 담기
				LocationVO vo = new LocationVO(lno, writer, title, content, regdate);

				//list에 객체 저장
				list.add(vo);

			}
			
			//검색어가 없는 경우
			if(search == null) {
				rs = pstmt.executeQuery();
				while (rs.next()) {

					//한 행씩 받기
					int lno = rs.getInt("lno");
					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp regdate = rs.getTimestamp("regdate");

					//LocationVO 객체 생성 후 생성자로 한번에 담기
					LocationVO vo = new LocationVO(lno, writer, title, content, regdate);

					//list에 객체 저장
					list.add(vo);

				}
			}
		

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(conn, pstmt, rs);
	}

	//list 반환
	return list;
}





//글 내용 확인
public LocationVO loContent(String lno) {

	LocationVO vo = new LocationVO();

	String sql = "select * from location where lno = ?";


	try {

		conn = DriverManager.getConnection(url, uid, upw);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, lno);

		rs = pstmt.executeQuery();

		if(rs.next()) {
			vo.setLno(rs.getInt("lno"));
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
public void update(String lno, String title, String content) {

	String sql = "update location set title = ?, content = ? where lno = ?";

	try {
		conn = DriverManager.getConnection(url, uid, upw);
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, lno);

		pstmt.executeUpdate(); //void형이니까 여기서 끝

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(conn, pstmt, rs);
	}

}

//삭제메서드
public int delete(String lno) {
	int result = 0;
	String sql = "delete from location where lno = ?";

	try {
		conn = DriverManager.getConnection(url, uid, upw);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, lno);

		result = pstmt.executeUpdate();


	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(conn, pstmt, rs);
	}

	return result;
}





}
