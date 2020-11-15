package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.objects.UserDTO;

import java.util.Optional;

public interface UserService {

    boolean userExists(String email);

    Optional<User> getUserById(Long userId);
    Optional<User> getUserByEmail(String email);

    User save(User user);
    User register(UserDTO userDTO) throws Exception;
}
