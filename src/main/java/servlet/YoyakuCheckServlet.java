package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.YoyakuDAO;
import model.Kanja;
import model.Yoyaku;

@WebServlet("/yoyakuCheck")
public class YoyakuCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        System.out.println("=== セッション確認 ===");
        if (session == null) {
            System.out.println("セッションなし");
            response.sendRedirect("login");
            return;
        } else {
            Enumeration<String> names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                Object value = session.getAttribute(name);
                System.out.println(name + " = " + value);
            }
        }

        Kanja kanja = (Kanja) session.getAttribute("loginUser");
        if (kanja == null) {
            response.sendRedirect("login");
            return;
        }

        String today = LocalDate.now().toString();
        int nowHour = LocalTime.now().getHour();
        List<String> times = List.of("9-10", "10-11", "11-12", "16-17", "17-18");

        YoyakuDAO dao = new YoyakuDAO();

        // 今日の予約があるか
        Yoyaku yoyaku = dao.findReservation(kanja.getKanjaID(), today);

        System.out.println("=== 予約チェック ===");
        System.out.println("kanjaID = " + kanja.getKanjaID());
        System.out.println("today = " + today);
        System.out.println("取得した予約オブジェクト = " + yoyaku);

        if (yoyaku != null) {
            response.sendRedirect(request.getContextPath() + "/yoyakuari");
            return;
        }

        // 現在時刻より後の空き枠があるか
        boolean hasFutureSlot = times.stream().anyMatch(t -> {
            int start = Integer.parseInt(t.split("-")[0]);
            return start > nowHour && dao.countByTime(today, t) < 6;
        });

        if (!hasFutureSlot) {
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakung.jsp").forward(request, response);
            return;
        }

        // 予約可能 → 時間選択へ
        response.sendRedirect("yoyakuSelect");
    }
}