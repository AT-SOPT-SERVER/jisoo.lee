package org.sopt.service;

import org.sopt.Exception.BaseException;
import org.sopt.domain.User;
import org.sopt.dto.response.UserResponse;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.sopt.Exception.ErrorCode;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(String name) {
        if (name == null || name.isBlank()) {
            throw new BaseException(ErrorCode.EMPTY_NAME);
        }

        User user = new User(name);
        userRepository.save(user);

        return UserResponse.success(user.getName(), user.getId());
    }
}