package be.thomasmore.graduaten.diceroll.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuthenticatedUser implements UserDetails {
    // Authenticated User implements UserDetails interface for use in the security configuration for authentication and authorization
    // User entity is injected into this object

    // Attributes
    private User user = new User();

    // Constructor
    public AuthenticatedUser() {
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    // Methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Convert database values to values of type GrantedAuthority for authorization purposes
        Set<GrantedAuthority> auth = new HashSet<>();
        user.getAuthorities().forEach(x -> auth.add(new SimpleGrantedAuthority("ROLE_" + x.getName())));
        return auth;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
