package fr.epsi.tp.redtweet.security;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Fx on 02/04/2015.
 * Handle security
 * Forbid access to not authenticated users
 */
@WebFilter(filterName = "ApiFilter", urlPatterns = {"/api/*"})
public class ApiFilter implements Filter {

    private Logger logger = Logger.getLogger(ApiFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        logger.info("API access: " + request.getPathInfo());

        if (request.getPathInfo().equals("/auth")) {
            chain.doFilter(req, resp);
        }

        HttpSession session = request.getSession();

        // Not connected ? Then you get 401 UNAUTHORIZED
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
