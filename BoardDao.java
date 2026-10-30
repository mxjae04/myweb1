package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.BoardDto;

public class BoardDao {

    // DB 연결은 MembersDao에 만들어 둔 메소드를 재사용해요
    private Connection getConnection() throws Exception {
        return new MembersDao().getConnection();
    }

    // 게시글 등록
    public void insertBoard(BoardDto dto) throws Exception {
        String sql = "INSERT INTO BOARD_TBL (BOARD_NO, MEMBER_ID, IMAGE_NAME, IMAGE_DESC) "
                   + "VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, dto.getMember_id());
            pstmt.setString(2, dto.getImage_name());
            pstmt.setString(3, dto.getImage_desc());
            pstmt.executeUpdate();
        }
    }

    // 게시글 목록 조회 (최신 글이 위로)
    public List<BoardDto> selectBoardList() throws Exception {
        List<BoardDto> list = new ArrayList<>();

        String sql = "SELECT BOARD_NO, MEMBER_ID, IMAGE_NAME, IMAGE_DESC, REG_DATE, UPDATE_DATE "
                   + "FROM BOARD_TBL ORDER BY BOARD_NO DESC";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBoard_no(rs.getInt("BOARD_NO"));
                dto.setMember_id(rs.getString("MEMBER_ID"));
                dto.setImage_name(rs.getString("IMAGE_NAME"));
                dto.setImage_desc(rs.getString("IMAGE_DESC"));
                dto.setReg_date(rs.getTimestamp("REG_DATE"));
                dto.setUpdate_date(rs.getTimestamp("UPDATE_DATE"));
                list.add(dto);
            }
        }
        return list;
    }
}