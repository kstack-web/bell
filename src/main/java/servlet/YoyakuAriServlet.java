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

@WebServlet("/yoyakuari")
public class YoyakuAriServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Kanja kanja = (Kanja) session.getAttribute("loginUser");
        int kanjaID = kanja.getKanjaID();
        String today = LocalDate.now().toString();

        YoyakuDAO dao = new YoyakuDAO();
        Yoyaku yoyaku = dao.findReservation(kanjaID, today);
        
        //キー: loginYoyaku
        //値: yoyakuオブジェクト
        //保存場所: request（リクエストスコープ）

        request.setAttribute("loginYoyaku", yoyaku);

        request.getRequestDispatcher("/WEB-INF/jsp/yoyakuari.jsp")
               .forward(request, response);

        System.out.println("通ってるよ。");
    }
}