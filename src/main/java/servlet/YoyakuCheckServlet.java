package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.YoyakuDAO;
import model.Kanja;
import model.Yoyaku;

@WebServlet("/yoyakuCheck")
public class YoyakuCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	//DBパス
        String dbPath = getServletContext().getRealPath("/WEB-INF/db/yoyaku.db");

        Kanja kanja = (Kanja) request.getSession().getAttribute("loginKanja");

        String today = LocalDate.now().toString();
        int nowHour = LocalTime.now().getHour();
        List<String> times = List.of("9-10", "10-11", "11-12", "16-17", "17-18");

        YoyakuDAO dao = new YoyakuDAO(dbPath);

        // ★ 今日の予約があるか
        Yoyaku yoyaku = dao.findTodayByKanja(kanja.getKanjaID());
        if (yoyaku != null) {
            request.setAttribute("loginYoyaku", yoyaku);
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakuari.jsp").forward(request, response);
            return;
        }


        // ★ 現在時刻より後の空き枠があるか
        boolean hasFutureSlot = times.stream().anyMatch(t -> {
            int start = Integer.parseInt(t.split("-")[0]);
            return start > nowHour && dao.countByTime(today, t) < 6;
        });

        if (!hasFutureSlot) {
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakung.jsp").forward(request, response);
            return;
        }

        // ★ 予約可能 → 時間選択へ
        response.sendRedirect("yoyakuSelect");
    }
}
