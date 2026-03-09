package servlet;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.YoyakuDAO;
import model.Kanja;

@WebServlet("/yoyakucancel")
public class YoyakuCancelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Kanja kanja = (Kanja) session.getAttribute("loginUser");
        int kanjaID = kanja.getKanjaID();
        String today = LocalDate.now().toString();

        YoyakuDAO dao = new YoyakuDAO();
        dao.cancelTodayByKanja(kanjaID, today);

        request.getRequestDispatcher("/WEB-INF/jsp/yoyakucancel.jsp")
               .forward(request, response);
    }
}