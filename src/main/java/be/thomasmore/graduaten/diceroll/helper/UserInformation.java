package be.thomasmore.graduaten.diceroll.helper;

import be.thomasmore.graduaten.diceroll.entity.AuthenticatedUser;
import be.thomasmore.graduaten.diceroll.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInformation {

    public static User getAuthenticatedUser() {
        AuthenticatedUser authUser = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthenticatedUser) {
            authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        if (authUser == null) {
            return null;
        }
        else{
            return authUser.getUser();
        }
    }

    public static boolean checkAdminUser() {

        User authUser = getAuthenticatedUser();

        if (authUser != null) {
            return authUser.getAuthorities().stream().anyMatch(a -> a.getName().equals("Admin"));
        }

        return false;
    }
}
