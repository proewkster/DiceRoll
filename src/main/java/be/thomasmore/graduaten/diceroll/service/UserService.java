package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Authority;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.objects.UserDTO;
import be.thomasmore.graduaten.diceroll.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository _repo;
    private final ModelMapper _mapper;
    private final BCryptPasswordEncoder _encoder;
    private final AuthorityService authorityService;

    @Autowired
    public UserService(UserRepository repo, ModelMapper mapper, BCryptPasswordEncoder encoder, AuthorityService authService){
        this._repo = repo;
        this._mapper = mapper;
        this._encoder = encoder;
        this.authorityService = authService;
    }

    public boolean userExists(String email) {
        return this.findUserByEmail(email).isPresent();
    }

    public Optional<User> findUserById(Integer userID) {
        return _repo.findById(userID);
    }

    public User GetUser(Integer userID){return _repo.getOne(userID);}
    public Optional<User> findUserByEmail(String email) {
        return _repo.findUserByEmail(email);
    }

    public User save(User user) {
        return _repo.save(user);
    }

    public User register(UserDTO userDTO){
        //Encrypt the password
        userDTO.setPassword(_encoder.encode(userDTO.getPassword()));

        //Instantiate new User model
        User user = new User();

        //Map UserDTO model to User model
        _mapper.map(userDTO, user);

        //Set User model creation date
        user.setCreated(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        //Set default "User" role
        Authority auth = authorityService.findAuthorityByName("User").get();

        List<Authority> defaultAuthorities = new ArrayList<Authority>();
        defaultAuthorities.add(auth);

        user.setAuthorities(defaultAuthorities);

        //Set default "Enabled" state
        user.setEnabled(true);

        //Save the User model in the database
        return save(user);
        //return user;
    }
}
