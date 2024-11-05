package pers.lionlinzq.excel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.lionlinzq.excel.entity.User;
import pers.lionlinzq.excel.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void saveUserBatch(List<User> user) {
        userRepository.saveAll(user);
    }


}