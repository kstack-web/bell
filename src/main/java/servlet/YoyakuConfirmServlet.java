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
import model.Yoyaku;

@WebServlet("/YoyakuConfirm")
public class YoyakuConfirmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("=== YoyakuConfirmServlet 起動 ===");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            System.out.println("セッションなし → yoyakunasi.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakunasi.jsp")
                   .forward(request, response);
            return;
        }

        Kanja kanja = (Kanja) session.getAttribute("loginUser");
        int kanjaID = kanja.getKanjaID();
        String today = LocalDate.now().toString();

        System.out.println("kanjaID = " + kanjaID);
        System.out.println("today = " + today);

        YoyakuDAO dao = new YoyakuDAO();
        boolean hasYoyaku = dao.hasTodayReservation(kanjaID, today);
        System.out.println("hasYoyaku = " + hasYoyaku);

        if (hasYoyaku) {
            Yoyaku yoyaku = dao.findReservation(kanjaID, today);
            request.setAttribute("loginYoyaku", yoyaku);

            System.out.println("→ yoyakuari.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakuari.jsp")
                   .forward(request, response);
        } else {
            System.out.println("→ yoyakunasi.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakunasi.jsp")
                   .forward(request, response);
        }
    }
}