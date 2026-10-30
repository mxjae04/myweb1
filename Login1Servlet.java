package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.MembersDao;



@WebServlet("/login1")
public class Login1Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 로그인 화면 보여주기
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login1.jsp").forward(request, response);
    }

    // 로그인 처리하기
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. 로그인 폼에서 아이디와 비밀번호 가져오기
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        try {
            // 2. DB에서 아이디와 비밀번호 확인하기
            MembersDao dao = new MembersDao();

            boolean result = dao.loginCheck(id, pw);

            // 3. 로그인 성공
            if (result) {

                // 세션 생성
                HttpSession session = request.getSession();

                // 로그인한 아이디 저장
                session.setAttribute("loginId", id);

                // 게시판 목록으로 이동
                response.sendRedirect(request.getContextPath() + "/board.jsp");

            } else {

                // 로그인 실패
                request.setAttribute("message", "아이디 또는 비밀번호가 틀렸습니다.");

                request.getRequestDispatcher("/login1.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("로그인 처리 중 오류가 발생했습니다.", e);
        }
    }
    
    
}