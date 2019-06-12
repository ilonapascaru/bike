package filtre;

import function.LogFunction;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
        System.out.println("bye!");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Logger logger = Logger.getLogger("Info");
        LogFunction logFunction = new LogFunction();
        String path = ((HttpServletRequest) request).getRequestURI();


        if (isException(path)) {
            System.out.println(path);
            chain.doFilter(request, response);
        } else {
            HttpSession session = ((HttpServletRequest) request).getSession();

            if (session.getAttribute("username") != null ) {
                logFunction.LoggerFun("info", session.getAttribute("username")+"" );
                logger.info(Thread.currentThread().getStackTrace()[2].getLineNumber() + "  you did it!");
                //logFunction.Log(logger);
                chain.doFilter(request, response);


            } else {
                ((HttpServletResponse) response).sendRedirect("/index.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
        Logger logger = Logger.getLogger("info");
        System.out.println("olla!");
        logger.info(Thread.currentThread().getStackTrace()[2].getLineNumber() + "  you did it again!");


    }

    private boolean isException(String path) {
        return path.equals("/index.jsp") || path.equals("/") || path.equals("/login")|| path.equals("/userInform");

    }

}
