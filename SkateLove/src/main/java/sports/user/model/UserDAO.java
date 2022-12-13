package sports.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;

import sports.util.JDBCUtil;


public class UserDAO {

	//1. 나자신의 객체를 생성해서 1개로 제한
	private static UserDAO instance = new UserDAO();

	//2. 직접 객체를 생성 할 수 없도록 생성자에 private
	private UserDAO() {
		//드라이버 클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버클래스 로드에러");
		}

	}

	//3. 외부에서 객체 생성을 요구할 때 getter메서드를 통해 1번의 객체를 반환
	public static UserDAO getInstance() {
		return instance;
	}

	//4.필요한 데이터 베이스 변수 선언
	public String URL = "jdbc:oracle:thin:@172.30.1.49:1521:xe";
	public String UID = "sports";
	public String UPW = "sports";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//5. 메서드
	
	
	//아이디 중복 확인하는 메서드
	public int idCheck(String id) {

		int result = 0;

		String sql = "select count(*) as total from users where id=?";

		try {

			//connection 객체
			conn= DriverManager.getConnection(URL, UID, UPW);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}

		return result;


	}

	//회원가입하는 메서드
	public void join(UserVO vo) {
		String sql = "insert into users values(?, ?, ?, ?, ?, ?)";

		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAge());
			pstmt.setString(5, vo.getPhoneno());
			pstmt.setString(6, vo.getGender());

			pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	}
	
	
	//로그인하는 메서드
	//반환값이 void가 아닌 int여야 하는 이유: 로그인 성공여부를 정수값으로 치환해서 판단하기 위해
	
	public UserVO login(String id, String pw) {
		
		UserVO vo = null;
		
		String sql = "select * from users where id =? and pw=? ";
		
		
		
		try {
			//준비해둔 sql 구문을 데이터베이스와 연동
			conn= DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			//?값 세팅
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			//sql 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id2 = rs.getString("id"); 
				String name = rs.getString("name"); 
				String age = rs.getString("age"); 
				String phonenumber = rs.getString("phoneno"); 
				String gender = rs.getString("gender");
				
				
				vo = new UserVO(id2, null, name, age, phonenumber, gender);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	//회원 정보 조회 메서드
	public UserVO getInfo(String id) {
		
		UserVO vo= new UserVO();
		
		String sql = "select * from users where id = ?";
		
		try {
			
			conn= DriverManager.getConnection(URL, UID, UPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id2 = rs.getString("id"); 
				String name = rs.getString("name"); 
				String age = rs.getString("age"); 
				String phonenumber = rs.getString("phoneno"); 
				String gender = rs.getString("gender");
				
				vo = new UserVO(id2, null, name, age, phonenumber, gender);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return vo;
	}
	
	
	
	
	
	
	
	//정보수정하는 메서드
	public int update(String id, String pw, String name, String age, String phoneno, String gender) {
		
		int result = 0; //id 저장하기 위한 변수
		String sql = "update users set pw=?, name=?, age=?, phoneno=?, gender=? where id=?";
		
		try {
			
			conn= DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2,name);
			pstmt.setString(3, age);
			pstmt.setString(4, phoneno);
			pstmt.setString(5, gender);
			pstmt.setString(6, id);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}

	
	//회원 탈퇴
	public int delete(String id) {
		
		int result = 0;
		String sql = "delete from users where id=?";
		
		try {
			
			conn= DriverManager.getConnection(URL, UID, UPW);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}

}

