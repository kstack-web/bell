package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/yoyakuari")
public class YoyakuAriServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッション取得（ログイン確認）
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginKanja") == null) {
            // 未ログインならログイン画面へ
            response.sendRedirect("login");
            return;
        }

        // 予約あり画面へ
        request.getRequestDispatcher("/WEB-INF/jsp/yoyakuari")
               .forward(request, response);
    }
}
