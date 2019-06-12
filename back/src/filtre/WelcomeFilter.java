package filtre;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebFilter(filterName = "WelcomeFilter")
public class WelcomeFilter implements Filter {
    public void destroy() {

        System.out.println("bye!");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        if (((HttpServletRequest) req).getSession().getAttribute("username") == "") {
            out.println("YOU HAVE NO ACCESS!");
        } else {

            chain.doFilter(req, resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("olla!");
    }

}
