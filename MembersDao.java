package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import DTO.MembersDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MembersDao {
	// (Data Access Object)는 데이터베이스 작업을 담당하는 클래스
	// DB와 데이터를 주고받는 역할 (등록 조회 수정 삭제

	// DB 연결 메소드
	public Connection getConnection() throws Exception {

		// Oracle JDBC 드라이버 로드
		Class.forName("oracle.jdbc.OracleDriver");
		// DB 연결하기
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:testdb", 
				"mj", 
				"1234"
				);
		return con; // 연결된 DB 객체 반환 -(DB 연결 객체를 메소드를 호출한 곳으로 돌려주는 코드)
	}

	public void insertMember(MembersDto dto) throws Exception {
		// DB 연결하기
		Connection con = getConnection();

		// SQL 작성
		String sql = "INSERT INTO MEMBER_TBL " + "(MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_EMAIL, MEMBER_TEL) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		//SQL문을 실행할 준비
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//DTO에서 값 가져오기
		pstmt.setString(1, dto.getMember_id());
		pstmt.setString(2, dto.getMember_pw());
		pstmt.setString(3, dto.getMember_name());
		pstmt.setString(4, dto.getMember_email());
		pstmt.setString(5, dto.getMember_tel());
		
		//DB에 실제 저장
		pstmt.executeUpdate();
		
		//연결 종료
		pstmt.close();
		con.close();
	}
	// 로그인 확인 메소드
	public boolean loginCheck(String id, String pw) throws Exception {

	    Connection con = getConnection();

	    String sql = "SELECT MEMBER_ID FROM MEMBER_TBL "
	               + "WHERE MEMBER_ID = ? AND MEMBER_PW = ?";

	    PreparedStatement pstmt = con.prepareStatement(sql);

	    pstmt.setString(1, id);
	    pstmt.setString(2, pw);

	    ResultSet rs = pstmt.executeQuery();

	    boolean result = rs.next();

	    rs.close();
	    pstmt.close();
	    con.close();

	    return result;
	}

	

}
