package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.User;

public interface UserService {
    User getUserById(String userId);
}
