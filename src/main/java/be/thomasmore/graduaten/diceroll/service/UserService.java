package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Authority;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.*;
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

    public List<User> findAll() { return _repo.findAll(); }

    public Optional<User> findUserById(Integer userID) {
        return _repo.findById(userID);
    }

    public Optional<User> findUserByEmail(String email) {
        return _repo.findUserByEmail(email);
    }

    public User save(User user) {
        return _repo.save(user);
    }

    public void delete(User user) {
        _repo.save(anonymizeUser(user));
    }

    public void deleteUserById(int userId) {

        // Get user account
        User user = findUserById(userId).get();

        // Anonymize user account
        _repo.save(anonymizeUser(user));
    }

    public User register(RegisterUserDTO registerUserDTO){
        //Encrypt the password
        registerUserDTO.setPassword(_encoder.encode(registerUserDTO.getPassword()));

        //Instantiate new User model
        User user = new User();

        //Map UserDTO model to User model
        _mapper.map(registerUserDTO, user);

        //Set User model creation date
        user.setCreated(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        //Set default "User" role
        user.setAuthorities(addUserRole(new ArrayList<Authority>(), "User"));

        //Set default "Enabled" state
        user.setEnabled(true);

        //Save the User model in the database
        return save(user);
    }

    // Change user account from admin module
    public User updateUser(UserMgmtDTO changes) {

        // Get current user information from database
        User currentUser = findUserById(changes.getUserId()).get();

        // Set user roles
        if (changes.isUserRole()) {
            changes.setAuthorities(addUserRole(currentUser.getAuthorities(), "User"));
        }
        else {
            changes.setAuthorities(removeUserRole(currentUser.getAuthorities(), "User"));
        }

        if (changes.isAdminRole()) {
            changes.setAuthorities(addUserRole(currentUser.getAuthorities(), "Admin"));
        }
        else {
            changes.setAuthorities(removeUserRole(currentUser.getAuthorities(), "Admin"));
        }

       // Map changes to user entity
        _mapper.map(changes, currentUser);

        // Save changes and return updated user entity
        return save(currentUser);
    }

    // Change user's contact details only
    public User updateAuthUser(UserChangeContactDTO changes) {

        // Get current authenticated user information
        User user = UserInformation.getAuthenticatedUser();

        // Update user information
        _mapper.map(changes, user);

        // Save changes to database
        return save(user);
    }

    // Change user's personal information only
    public User updateAuthUser(UserChangePersonalDTO changes) {

        // Get current authenticated user information
        User user = UserInformation.getAuthenticatedUser();

        // Update user information
        _mapper.map(changes, user);

        // Save changes to database
        return save(user);
    }

    // Change user's email address only
    public User updateAuthUser(UserChangeEmailDTO changes) {

        // Get current authenticated user information
        User user = UserInformation.getAuthenticatedUser();

        // Update user information
        user.setEmail(changes.getNewEmail());

        // Save changes to database
        return save(user);
    }

    // Change user's password
    public User updateAuthUser(UserChangePasswordDTO changes) {

        // Get current authenticated user information
        User user = UserInformation.getAuthenticatedUser();

        // Update user information
        user.setPassword(_encoder.encode(changes.getNewPassword()));

        // Save changes to database
        return save(user);
    }

    public User anonymizeUser(User user) {
        user.setEmail("anonymized");
        user.setFirstName("anonymized");
        user.setLastName("anonymized");
        user.setPassword("anonymized");
        user.setZipCode("0000");
        user.setCity("anonymized");
        user.setStreetAddress("anonymized");
        user.setPhoneNumber("anonymized");
        user.setMobileNumber("anonymized");
        user.setEnabled(false);
        user.setBirthdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        return user;
    }

    private List<Authority> addUserRole(List<Authority> authorities, String role) {
        Authority auth = null;

        // Get authority from database
        try {
            auth = authorityService.findAuthorityByName(role).get();
        }
        catch(Exception ex) {
            // Specified authority not found, return list without update
            return authorities;
        }

        // If list does not contain the specified authority, add it
        if (authorities.stream().noneMatch(a -> a.getName().equals(role))) {
            authorities.add(auth);
        }

        // Return authority list
        return authorities;
    }

    private List<Authority> removeUserRole(List<Authority> authorities, String role) {
        Authority auth = null;

        // Get authority from database
        try {
            auth = authorityService.findAuthorityByName(role).get();
        }
        catch(Exception ex) {
            // Specified authority not found, return list without update
            return authorities;
        }

        // If list does not contain the specified authority, add it
        if (authorities.stream().anyMatch(a -> a.getName().equals(role))) {
            authorities.remove(auth);
        }

        // Return authority list
        return authorities;
    }
}
