package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	
    	
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
        String ctx  = request.getContextPath();
        String uri  = request.getRequestURI();
        String path = uri.substring(ctx.length()); // 例: "/login", "/css/style.css"

        // ログイン不要（ループ防止込み）
        if (path.startsWith("/index.html")||
        		path.startsWith("/syokai.html")||
        		path.equals("/login")//入れるとループ防止
                || path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/img/")
                || path.equals("/favicon.ico")) {

            chain.doFilter(request, response);
            return;
        }

        // 認証チェック
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect(ctx + "/login");
            return;
          
        }
        System.out.println("フィルター使えてる");

        chain.doFilter(request, response);
    }
}
