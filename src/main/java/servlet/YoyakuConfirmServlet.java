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
import model.Yoyaku;

	@WebServlet("/YoyakuConfirm")
	public class YoyakuConfirmServlet extends HttpServlet {

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	//DBパス
	        String dbPath = getServletContext().getRealPath("/WEB-INF/db/yoyaku.db");

	        System.out.println("=== YoyakuConfirmServlet 起動 ===");

	        HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("loginKanja") == null) {
	            System.out.println("セッションなし → よやくなしへ");
	            response.sendRedirect(request.getContextPath() + "/yoyakunasi");
	            return;
	        }

	        Kanja kanja = (Kanja) session.getAttribute("loginKanja");
	        int kanjaID = kanja.getKanjaID();
	        System.out.println("kanjaID = " + kanjaID);

	        YoyakuDAO dao = new YoyakuDAO(dbPath);
	        boolean hasYoyaku = dao.hasTodayYoyaku(kanjaID);
	        System.out.println("hasYoyaku = " + hasYoyaku);

	        if (hasYoyaku) {
	            // ★ ここを追加
	            Yoyaku yoyaku = dao.findTodayByKanja(kanjaID);
	            session.setAttribute("loginYoyaku", yoyaku);

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
