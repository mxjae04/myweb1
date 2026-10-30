package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout1")
public class Logout1Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 세션이 있을 때만 가져오기 (없으면 새로 만들지 않음)
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // loginId를 포함한 세션 전체 삭제
        }

        // 로그인 화면으로 이동
        response.sendRedirect(request.getContextPath() + "/login1.jsp");
    }
}