package servlet;

import java.io.IOException;

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
    	
    	//DBパス
        String dbPath = getServletContext().getRealPath("/WEB-INF/db/yoyaku.db");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginKanja") == null) {
            response.sendRedirect("login");
            return;
        }

        Kanja kanja = (Kanja) session.getAttribute("loginKanja");
        int kanjaID = kanja.getKanjaID();

        YoyakuDAO dao = new YoyakuDAO(dbPath);
        dao.cancelTodayByKanja(kanjaID);

        request.getRequestDispatcher("/WEB-INF/jsp/yoyakucancel.jsp")
        .forward(request, response);

 
    }
}
