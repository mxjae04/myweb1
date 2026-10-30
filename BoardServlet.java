package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import DAO.BoardDao;
import DTO.BoardDto;

@WebServlet("/board")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,   // 1MB 초과 시 디스크에 임시 저장
        maxFileSize = 1024 * 1024 * 10,    // 파일 하나 최대 10MB
        maxRequestSize = 1024 * 1024 * 50  // 요청 전체 최대 50MB
)
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. 로그인 확인
        HttpSession session = request.getSession(false);
        String loginId = (session == null) ? null : (String) session.getAttribute("loginId");
        if (loginId == null) {
            response.sendRedirect(request.getContextPath() + "/login1.jsp");
            return;
        }

        // 2. 폼 값 가져오기 (register.jsp의 name과 일치해야 해요)
        String imageDesc = request.getParameter("imageDesc");
        Part imagePart = request.getPart("image");

        if (imagePart == null || imagePart.getSize() == 0) {
            response.sendRedirect(request.getContextPath() + "/register.jsp");
            return;
        }

        // 3. 이미지 파일인지 확인
        String contentType = imagePart.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            response.sendRedirect(request.getContextPath() + "/register.jsp");
            return;
        }

        // 4. 저장 폴더 준비 (webapp/upload)
        String uploadPath = getServletContext().getRealPath("/upload");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // 5. 파일명 중복 방지를 위해 UUID를 붙여서 저장
        String originalName = imagePart.getSubmittedFileName();
        String savedName = UUID.randomUUID() + "_" + originalName;
        imagePart.write(uploadPath + File.separator + savedName);

        // 6. DB에 저장
        try {
            BoardDto dto = new BoardDto();
            dto.setMember_id(loginId);
            dto.setImage_name(savedName);
            dto.setImage_desc(imageDesc);

            new BoardDao().insertBoard(dto);

            // 7. 목록으로 이동 (JSP가 아니라 서블릿으로)
            response.sendRedirect(request.getContextPath() + "/board");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("게시글 등록 중 오류가 발생했습니다.", e);
        }
    }
}