package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(String name) {
        User user = new User(name);
        userRepository.save(user);
        return user.getId();
    }
}
