package be.thomasmore.graduaten.diceroll.helper;

import be.thomasmore.graduaten.diceroll.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInformation {

    public static User getAuthenticatedUser() {
        User authUser = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return authUser;
    }
}
