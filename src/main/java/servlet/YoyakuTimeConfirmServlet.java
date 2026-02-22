package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/yoyakuTimeConfirm")
public class YoyakuTimeConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String time = request.getParameter("time");
        request.setAttribute("time", time);

        request.getRequestDispatcher("/WEB-INF/jsp/yoyakutimeconfirm.jsp")
               .forward(request, response);
    }
}
