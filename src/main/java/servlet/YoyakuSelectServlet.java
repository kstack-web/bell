package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.YoyakuDAO;

@WebServlet("/yoyakuSelect")
public class YoyakuSelectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // ★ ログインチェックは後でフィルターに移動するので削除予定

        String today = LocalDate.now().toString();
        int nowHour = LocalTime.now().getHour();

        List<String> times = List.of("9-10", "10-11", "11-12", "16-17", "17-18");
        YoyakuDAO dao = new YoyakuDAO();
        List<String> available = new ArrayList<>();

        // ★ 現在時刻より後で、かつ空きがある枠だけを抽出
        for (String t : times) {
            int startHour = Integer.parseInt(t.split("-")[0]);

            if (startHour > nowHour && dao.countByTime(today, t) < 6) {
                available.add(t);
            }
        }

        // ★ 空き枠がない場合は満員ページへ
        if (available.isEmpty()) {
            request.getRequestDispatcher("/WEB-INF/jsp/yoyakung.jsp").forward(request, response);
            return;
        }

        // ★ 空き枠を JSP に渡す
        request.setAttribute("availableTimes", available);
        request.getRequestDispatcher("/WEB-INF/jsp/yoyakuselect.jsp").forward(request, response);
    }
}

