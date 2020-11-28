package be.thomasmore.graduaten.diceroll.objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //**********************
        //Code below is to test values passed to the authentication request. Uncomment for troubleshooting
        //**********************
        //HttpSession session = request.getSession();
        //DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        //if (savedRequest != null) {
        //    String redirectedURL = savedRequest.getRedirectUrl();
        //}
        //
        //String referer = request.getHeader("referer");
        //
        //String source = request.getParameter("from").toString();

        // Check for the presence of a saved request
        // This indicates that the user browsed directly to a secured page and should be redirected to that page after successful login
        if (request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") != null) {
            super.onAuthenticationSuccess(request, response, authentication);
        }

        // If a 'from' parameter is present in the request, the login was request through the navbar.
        // After login, user should be redirected to page where login was requested
        else if (request.getParameter("from") != null) {
            response.sendRedirect(request.getParameter("from"));
        }

        // In all other cases, user will be redirected to homepage
        else {
            response.sendRedirect("/");
        }
    }
}
