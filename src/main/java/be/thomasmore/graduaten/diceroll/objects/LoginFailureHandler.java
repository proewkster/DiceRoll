package be.thomasmore.graduaten.diceroll.objects;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        if (httpServletRequest.getParameter("from") != null) {
            httpServletResponse.sendRedirect(httpServletRequest.getParameter("from"));
        }

        else { httpServletResponse.sendRedirect("/login?error=true"); }
    }
}
