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

@WebServlet("/yoyakuExec")
public class YoyakuExecServlet extends HttpServlet {

   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String date=java.time.LocalDate.now().toString();
    	String time=request.getParameter("time");
    	
    	
    	//DBパス
        String dbPath = getServletContext().getRealPath("/WEB-INF/db/yoyaku.db");
    	
    	HttpSession session = request.getSession();
        //ログイン時にオブジェクトごとセッションに入れていた
    	Kanja loginUser = (Kanja) session.getAttribute("loginUser");

    	int kanjaID = loginUser.getKanjaID();
    	
    	
    	 
    	 YoyakuDAO dao1=new YoyakuDAO(dbPath);
    	 
   

    	// ★ 同日の予約がすでにあるかチェック
    	//if (dao1.existsByDateAndKanjaID(date, kanjaID)) {
    	  //  request.setAttribute("error", "同じ日に複数の予約はできません。");
    	    //request.getRequestDispatcher("/WEB-INF/jsp/yoyakuselect.jsp").forward(request, response);
    	    //return;
    	

   
    	 
    	 //予約登録
    	 dao1.insert(date, time, kanjaID);
    	 request.setAttribute("reservedDate", date);
    	 request.setAttribute("reservedTime", time);
    	 //完了画面
    	 request.getRequestDispatcher("/WEB-INF/jsp/yoyakuok.jsp").forward(request, response);
    	
    }

       
}


