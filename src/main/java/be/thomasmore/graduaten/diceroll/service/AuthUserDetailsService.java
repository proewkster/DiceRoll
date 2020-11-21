package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    //Attributes
    private UserRepository _repo; //Inject User Repository to retrieve user information from DB

    //Constructors
    @Autowired
    public AuthUserDetailsService(UserRepository repo) {
        this._repo = repo;
    }

    //Methods
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //Find a user in the DB with matching email address
        Optional<User> user = _repo.findUserByEmail(s);

        //Throw exception if user is not found
        user.orElseThrow(() -> new UsernameNotFoundException("No user found for email address: " + s));

        //Return user found in DB. User entity implements the UserDetails interface, so type will match
        //.get() method to extract actual User object from the Optional<>
        return user.get();
    }
}
