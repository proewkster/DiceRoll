package be.thomasmore.graduaten.diceroll.service;

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
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository _repo;
    private final ModelMapper _mapper;
    private final BCryptPasswordEncoder _encoder;

    @Autowired
    public UserServiceImpl(UserRepository repo, ModelMapper mapper, BCryptPasswordEncoder encoder){
        this._repo = repo;
        this._mapper = mapper;
        this._encoder = encoder;
    }

    public boolean userExists(String email) {
        return this.getUserByEmail(email).isPresent();
    }

    public Optional<User> getUserById(String userID) {
        return _repo.findById(userID);
    }

    public Optional<User> getUserByEmail(String email) {
        return _repo.findUserByEmail(email);
    }

    public User save(User user) {
        return _repo.save(user);
    }

    public User register(UserDTO userDTO) throws Exception{
        //Encrypt the password
        userDTO.setPassword(_encoder.encode(userDTO.getPassword()));

        //Instantiate new User model
        User user = new User();

        //Map UserDTO model to User model
        _mapper.map(userDTO, user);

        //Set new UID for User model
        user.setUserId(UUID.randomUUID().toString());

        //Set User model creation date
        user.setCreated(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        //Set default User role
        user.setRole("user");

        //Save the User model in the database
        //return save(user); --> Temporarily disabled until DB connection is restored
        return user;
    }
}
