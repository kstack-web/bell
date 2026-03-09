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
    	
    	
    	HttpSession session = request.getSession();
        //ログイン時にオブジェクトごとセッションに入れていた
    	Kanja loginUser = (Kanja) session.getAttribute("loginUser");

    	int kanjaID = loginUser.getKanjaID();
    	
    	
    	 
    	 YoyakuDAO dao1=new YoyakuDAO();
    	 
  
    	 
    	 //予約登録
    	 dao1.insert(date, time, kanjaID);
    	 request.setAttribute("reservedDate", date);
    	 request.setAttribute("reservedTime", time);
    	 //完了画面
    	 request.getRequestDispatcher("/WEB-INF/jsp/yoyakuok.jsp").forward(request, response);
    	
    }

       
}


