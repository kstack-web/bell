package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.KanjaDAO;
import model.Kanja;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String kanjaParam = request.getParameter("kanjaID");
        String bday = request.getParameter("bday");

        kanjaParam = kanjaParam == null ? "" : kanjaParam.trim();
        bday = bday == null ? "" : bday.trim();

        // ① 未入力
        if (kanjaParam.isEmpty() || bday.isEmpty()) {
            request.setAttribute("error", "患者番号と生年月日は必須です。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        // ② 形式チェック（患者ID）
        if (!kanjaParam.matches("^[0-9]+$")) {
            request.setAttribute("error", "患者番号は半角数字で入力してください。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        // ②-2 形式チェック（生年月日）
        // yyyy-mm-dd または yyyy/mm/dd を許可する例
        if (!bday.matches("^\\d{4}[-/]\\d{2}[-/]\\d{2}$")) {
            request.setAttribute("error", "生年月日は yyyy-mm-dd 形式で入力してください。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        int kanjaID = Integer.parseInt(kanjaParam);

        // ③ DAO検索
        KanjaDAO dao = new KanjaDAO();
        Kanja kanja = dao.login(kanjaID, bday);

        // ④ 該当なし
        if (kanja == null) {
            request.setAttribute("error", "患者番号 または 生年月日が違います。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

     // ⑤ ログイン成功
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", kanja);
     
      
        // 今日の予約がない → 通常の予約ページへ
        response.sendRedirect(request.getContextPath() + "/yoyaku");
        
        System.out.println("kanjaID=" + kanja.getKanjaID());
        System.out.println("kanjaName=" + kanja.getKanjaName());
        
        System.out.println("loginUser = " + session.getAttribute("loginUser"));
       
    }

      


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
               .forward(request, response);
       
    }
    


}